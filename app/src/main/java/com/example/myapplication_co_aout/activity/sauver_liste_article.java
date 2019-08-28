package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_list_article;
import com.example.myapplication_co_aout.model.Liste_article;
import com.example.myapplication_co_aout.model.Liste_souhait_model;

public class sauver_liste_article extends AppCompatActivity {

    private static EditText edit_nom_article;
    private static Button ajouter_article;
    private static Button retour;
    private static TextView nom_string_souhait;

    private Database_list_article db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauver_liste_article);

        edit_nom_article = (EditText) findViewById(R.id.edit_nom_article);

        ajouter_article= (Button) findViewById(R.id.ajouter_article);
        retour = (Button) findViewById(R.id.retour);

        nom_string_souhait = (TextView) findViewById(R.id.nom_string_souhait);

        db = new Database_list_article(getApplicationContext());



        nom_string_souhait.setText(Put_souhait_personne.getSouhait1());


        ajouter_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String article = edit_nom_article.getText().toString();
                db.open();
                if(!article.equals("")){
                    Liste_article liste = new Liste_article(article);
                    if(db.addListArticle(liste,Put_souhait_personne.getSouhait1(),Login.getUtilisateurPrincipale().getUsername())!=-1){
                        Toast.makeText(sauver_liste_article.this, "Vorte liste de souhait a été créé", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(sauver_liste_article.this, "Le nom de la liste existe déjà", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(sauver_liste_article.this, "le nom de la liste ou le nom du destinataire est manquant", Toast.LENGTH_LONG).show();
                }
                db.close();
                edit_nom_article.setText("");
            }
        });



        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),display_liste_article.class));
            }
        });
    }
}
