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

public class mod_liste_article extends AppCompatActivity {

    private static EditText ancien_article;
    private static EditText nouveau_article;

    private static Button modifier;
    private static Button retour;

    private Database_list_article db;
    private Database_article db_infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_liste_article);

        ancien_article = (EditText) findViewById(R.id.ancien_article);
        nouveau_article = (EditText) findViewById(R.id.nouveau_article);



        modifier = (Button) findViewById(R.id.modifier);
        retour = (Button) findViewById(R.id.retour);

        db = new Database_list_article(getApplicationContext());
        db_infos = new Database_article(getApplicationContext());

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String O_ancien_article = ancien_article.getText().toString();

                String O_nouveau_article = nouveau_article.getText().toString();

                String O_souhait = Put_souhait_personne.getSouhait1();
                String O_utilisateur = Login.getUtilisateurPrincipale().getUsername();

                db.open();
                db_infos.open();
                if(!O_ancien_article.equals("") && !O_nouveau_article.equals("")){


                    if(db.modListArticle(O_ancien_article, O_nouveau_article,O_souhait,O_utilisateur)>0){
                        db_infos.modNomArticle(O_utilisateur,O_souhait,O_ancien_article,O_nouveau_article);

                        Toast.makeText(mod_liste_article.this, "Le nom de l'article "+O_ancien_article+" a été modifié", Toast.LENGTH_LONG).show();
                        ancien_article.setText("");
                        nouveau_article.setText("");
                    }
                    else{
                        Toast.makeText(mod_liste_article.this, "Retappez le nom de l'article à modifier", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(mod_liste_article.this, "Veuillez taper un nom d'article", Toast.LENGTH_LONG).show();
                }
                db_infos.close();
                db.close();

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
