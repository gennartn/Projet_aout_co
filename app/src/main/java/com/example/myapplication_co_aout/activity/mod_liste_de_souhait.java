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

public class mod_liste_de_souhait extends AppCompatActivity {

    private static EditText souhait;
    private static EditText personne;
    private static EditText nouveau_souhait;
    private static EditText nouvelle_personne;

    private static Button modifier;
    private static Button retour;

    private Database_list_souhait db;
    private Database_list_article db_article;
    private Database_article db_infos;
    private Database_personne db_pers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_liste_de_souhait);

        souhait = (EditText) findViewById(R.id.souhait12);
        personne = (EditText) findViewById(R.id.personne13);
        nouveau_souhait = (EditText) findViewById(R.id.nouveau_souhait);
        nouvelle_personne= (EditText) findViewById(R.id.nouvelle_personne);


        modifier = (Button) findViewById(R.id.modifier);
        retour = (Button) findViewById(R.id.retour);

        db = new Database_list_souhait(getApplicationContext());

        db_article = new Database_list_article(getApplicationContext());

        db_infos = new Database_article(getApplicationContext());

        db_pers = new Database_personne(getApplicationContext());

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String O_souhait = souhait.getText().toString();
                String O_personne = personne.getText().toString();
                String O_nouveau_souhait = nouveau_souhait.getText().toString();
                String O_nouvelle_personne = nouvelle_personne.getText().toString();
                String O_utilisateur = Login.getUtilisateurPrincipale().getUsername();
                db.open();
                db_infos.open();
                db_article.open();
                db_pers.open();
                if(!O_souhait.equals("") && !O_personne.equals("") && !O_nouveau_souhait.equals("") && !O_nouvelle_personne.equals("")){


                    if(db.modListSouhait(O_souhait, O_personne, O_nouveau_souhait,O_nouvelle_personne,O_utilisateur)>0){
                        db_infos.modNomSouhait(O_utilisateur,O_souhait,O_nouveau_souhait);
                        db_article.modNomSouhait(O_utilisateur, O_souhait, O_nouveau_souhait);
                        db_pers.modNomPersonne(O_utilisateur,O_personne,O_nouvelle_personne);

                        Toast.makeText(mod_liste_de_souhait.this, "Le nom de la liste de souhait a été modifié", Toast.LENGTH_LONG).show();
                        souhait.setText("");
                        personne.setText("");
                        nouveau_souhait.setText("");
                        nouvelle_personne.setText("");
                    }
                    else{
                        Toast.makeText(mod_liste_de_souhait.this, "Retappez le nom de la liste à modifier", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(mod_liste_de_souhait.this, "Vous n'avez rien noté ou vous n'avez pas tous complété", Toast.LENGTH_LONG).show();
                }
                db_pers.close();
                db_article.close();
                db_infos.close();
                db.close();

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),display_liste_souhait.class));
            }
        });
    }
}
