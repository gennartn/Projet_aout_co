package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_article;
import com.example.myapplication_co_aout.Sql.Database_list_article;
import com.example.myapplication_co_aout.Sql.Database_list_souhait;
import com.example.myapplication_co_aout.Sql.Database_personne;
import com.example.myapplication_co_aout.model.Liste_souhait_model;
import com.example.myapplication_co_aout.model.Log;

public class supp_liste_de_souhait extends AppCompatActivity {


    private static EditText souhait2;
    private static EditText personne2;

    private static Button supprimer3;
    private static Button retour;

    private Database_list_souhait db;
    private Database_list_article db_article;
    private Database_article db_infos;
    private Database_personne db_pers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supp_liste_de_souhait);

        souhait2 = (EditText) findViewById(R.id.souhait2);
        personne2= (EditText) findViewById(R.id.personne2);

        supprimer3 = (Button) findViewById(R.id.supprimer3);
        retour = (Button) findViewById(R.id.retour);

        db = new Database_list_souhait(getApplicationContext());

        db_article = new Database_list_article(getApplicationContext());

        db_infos = new Database_article(getApplicationContext());

        db_pers = new Database_personne(getApplicationContext());


        supprimer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personne = personne2.getText().toString();
                String souhait = souhait2.getText().toString();
                String username = Login.getUtilisateurPrincipale().getUsername();
                db.open();
                db_infos.open();
                db_article.open();
                db_pers.open();
                if(!souhait.equals("") && !personne.equals("")){

                    db_infos.suppInfosArticle(souhait, username);
                    db_article.suppArticle(souhait, username);
                    db_pers.supPersonne(personne, username);

                    if(db.supUnSouhait(souhait, personne, Login.getUtilisateurPrincipale().getUsername())>0){
                        Toast.makeText(supp_liste_de_souhait.this, "Vorte liste de souhait  a été supprimé", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(supp_liste_de_souhait.this, "Il n'y a rien a supprimer", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(supp_liste_de_souhait.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                }
                db_pers.close();
                db_article.close();
                db_infos.close();
                db.close();
                souhait2.setText("");
                personne2.setText("");
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), display_liste_souhait.class));
            }
        });

    }
}
