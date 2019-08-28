package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_article;
import com.example.myapplication_co_aout.Sql.Database_list_article;
import com.example.myapplication_co_aout.model.ListeArticleAdapter;
import com.example.myapplication_co_aout.model.ListeSouhaitAdapter;
import com.example.myapplication_co_aout.model.Liste_article;
import com.example.myapplication_co_aout.model.Liste_souhait_model;

import java.util.ArrayList;

public class display_liste_article extends AppCompatActivity {

    private Database_list_article db;
    private ListView liste_article;

    private String souhait;
    private ArrayList<Liste_article> array_article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_liste_article);

        db = new Database_list_article(getApplicationContext());
        array_article = new ArrayList<>();

        liste_article = (ListView) findViewById(R.id.liste_article);

        Intent intent= getIntent();
        souhait = intent.getStringExtra("EXTRA");
        db.open();
        ListView();
        db.close();

    }

    private void ListView(){
        Cursor cursor = db.getListArticle(souhait,Login.getUtilisateurPrincipale().getUsername());

        if(cursor.getCount()==0){
            Toast.makeText(display_liste_article.this, "Il n'y a pas encore d'article lié a cette liste", Toast.LENGTH_LONG).show();
        }
        else{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                array_article.add(new Liste_article(cursor.getString(3)));
                cursor.moveToNext();
            }
        }
        ListeArticleAdapter adapter = new ListeArticleAdapter(this, R.layout.activity_display_liste_article, array_article);
        liste_article.setAdapter(adapter);

    }
}
