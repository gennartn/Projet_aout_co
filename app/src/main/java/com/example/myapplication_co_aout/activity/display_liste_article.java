package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_list_article;
import com.example.myapplication_co_aout.model.ListeArticleAdapter;
import com.example.myapplication_co_aout.model.Liste_article;

import java.util.ArrayList;

public class display_liste_article extends AppCompatActivity {

    private Database_list_article db;
    private ListView liste_article;

    private static TextView nom_list_souhait;
    private static Button ajouter1;
    private static Button modifier1;
    private static Button supprimer1;
    private static Button retour1;

    private String souhait;
    private ArrayList<Liste_article> array_article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_liste_article);

        db = new Database_list_article(getApplicationContext());
        array_article = new ArrayList<>();

        liste_article = (ListView) findViewById(R.id.liste_article);

        nom_list_souhait = (TextView) findViewById((R.id.textView_souhait));
        ajouter1 = (Button) findViewById((R.id.creer));
        modifier1 = (Button) findViewById((R.id.modifier));
        supprimer1 = (Button) findViewById((R.id.supprimer));
        retour1 = (Button) findViewById((R.id.retour));


        souhait = Put_souhait_personne.getSouhait1();

        db.open();
        ListView();
        db.close();



        nom_list_souhait.setText(souhait);

        ajouter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sauver_liste_article.class);
                startActivity(intent);
            }
        });
        modifier1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mod_liste_article.class);
                startActivity(intent);
            }
        });
        supprimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),supp_liste_article.class);
                startActivity(intent);
            }
        });
        retour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Put_souhait_personne.class);
                /*intent.putExtra("EXTRA", Put_souhait_personne.getSouhait1());
                intent.putExtra("EXTR1", Put_souhait_personne.getPersonne1());*/
                startActivity(intent);
            }
        });


    }

    private void ListView(){
        Cursor cursor = db.getListArticle(souhait,Login.getUtilisateurPrincipale().getUsername());

        if(cursor.getCount()==0){
            Toast.makeText(display_liste_article.this, "Il n'y a pas encore d'article lié a cette liste", Toast.LENGTH_LONG).show();
        }
        else{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                array_article.add(new Liste_article(cursor.getString(2)));
                cursor.moveToNext();
            }
        }
        ListeArticleAdapter adapter = new ListeArticleAdapter(this, R.layout.activity_liste_article_adapter, array_article);
        liste_article.setAdapter(adapter);

    }
}
