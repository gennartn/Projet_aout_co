package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_list_article;
import com.example.myapplication_co_aout.Sql.Database_list_souhait;

public class supp_liste_article extends AppCompatActivity {

    private static EditText supprimer_article;


    private static Button supprimer;
    private static Button retour;

    private Database_list_article db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supp_liste_article);

        supprimer_article = (EditText) findViewById(R.id.supprimer_article);

        supprimer = (Button) findViewById(R.id.supprimer);
        retour = (Button) findViewById(R.id.retour);

        db = new Database_list_article(getApplicationContext());

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String article_s = supprimer_article.getText().toString();
                db.open();
                if(!article_s.equals("")){

                    if(db.supUnArticle(article_s,Put_souhait_personne.getSouhait1() ,Login.getUtilisateurPrincipale().getUsername())>0){
                        Toast.makeText(supp_liste_article.this, "L'article "+article_s+" a été supprimé", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(supp_liste_article.this, "Il n'y a rien a supprimer à ce nom", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(supp_liste_article.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                }
                db.close();
                supprimer_article.setText("");
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), display_liste_article.class));
            }
        });
    }
}
