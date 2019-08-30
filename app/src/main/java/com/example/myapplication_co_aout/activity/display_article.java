package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_article;
import com.example.myapplication_co_aout.model.Article;
import com.example.myapplication_co_aout.model.SpinnerAdapterArticle;

import java.util.ArrayList;

public class display_article extends AppCompatActivity {


    private static TextView nom_article;

    private Database_article db;

    private static Button ajouter_prix;
    private static EditText edit_prix;
    private static TextView view_prix;

    private static Button ajouter_magasin;
    private static EditText edit_magasin;
    private static TextView view_magasin;


    private static TextView cathegorie;
    private static Spinner spinner;

    private SpinnerAdapterArticle spinner_adapter;
    private ArrayList<String> arraylist = new ArrayList<>();


    private String article_string;

    private Article article;
    private Article nouveau_article;

    private static Button sauver;
    private static Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);

        ajouter_prix = (Button) findViewById(R.id.ajouter_prix);
        edit_prix = (EditText) findViewById(R.id.edit_prix);
        view_prix = (TextView) findViewById(R.id.view_prix);

        ajouter_magasin = (Button) findViewById(R.id.ajouter_magasin);
        edit_magasin = (EditText) findViewById(R.id.edit_magasin);
        view_magasin = (TextView) findViewById(R.id.view_magasin);

        sauver = (Button) findViewById(R.id.sauver);
        retour = (Button) findViewById(R.id.retour);

        spinner = findViewById(R.id.spinner);
        cathegorie = findViewById(R.id.cathegorie);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            article_string = bundle.getString("ARTICLE");
        }
        nom_article = (TextView) findViewById(R.id.nom_article);

        nom_article.setText(article_string);

        nouveau_article = new Article(article_string,"","","");

        db = new Database_article(getApplicationContext());
        db.open();

        article = db.getArticle(article_string, Login.getUtilisateurPrincipale().getUsername(),Put_souhait_personne.getSouhait1());
        if(article!=null){
            view_prix.setText(article.getPrix_article());
            view_magasin.setText(article.getNom_magasin());
        }
        db.close();



        ajouter_prix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_prix.getText().toString();
                if(!temp.equals("") && !temp.equals(" Prix(euro):")){
                    nouveau_article.setPrix_article(temp);
                    view_prix.setText(" "+nouveau_article.getPrix_article());
                    edit_prix.setText(" Prix(euro):");
                }
                else{
                    Toast.makeText(display_article.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                    edit_prix.setText(" Prix(euro):");
                }

            }
        });

        ajouter_magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_magasin.getText().toString();
                if(!temp.equals("") && !temp.equals(" Nom du magasin:")){
                    nouveau_article.setNom_magasin(temp);
                    view_magasin.setText(" "+nouveau_article.getNom_magasin());
                    edit_magasin.setText( "Nom du magasin:");
                }
                else{
                    Toast.makeText(display_article.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                    edit_magasin.setText(" Nom du magasin:");
                }

            }
        });


        spinner_adapter = new SpinnerAdapterArticle(this, R.layout.activity_display_article, arraylist);
        spinner.setAdapter(spinner_adapter);
        rempliArrayList();
        spinner_adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(onItemSelectedListener);



        sauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                String nom_souhait = Put_souhait_personne.getSouhait1();
                String utilisateur = Login.getUtilisateurPrincipale().getUsername();
                if(article!=null){
                    if(nouveau_article.getPrix_article().equals("")){
                        nouveau_article.setPrix_article(article.getPrix_article());
                    }
                    if(nouveau_article.getNom_cathegorie().equals("")){
                        nouveau_article.setNom_cathegorie(article.getNom_cathegorie());
                    }
                    if(nouveau_article.getNom_magasin().equals("")){
                        nouveau_article.setNom_magasin(article.getNom_article());
                    }

                }

                if(db.articlePresent(article_string,nom_souhait,utilisateur)){

                    db.modInfosArticle(article,nouveau_article, utilisateur, nom_souhait);
                }
                else{
                    db.addInfosArticle(nouveau_article,utilisateur,nom_souhait);
                }
                Toast.makeText(display_article.this, "Vos informations ont été sauvées", Toast.LENGTH_LONG).show();
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

    AdapterView.OnItemSelectedListener onItemSelectedListener= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cathegorie.setText(arraylist.get(position));
            if(cathegorie.getText().equals("")){
                if(article!=null){
                    cathegorie.setText(article.getNom_cathegorie());

                }
                else{
                    cathegorie.setText("Cathégorie:");
                }

            }
            else{
                cathegorie.setText(arraylist.get(position));
                nouveau_article.setNom_cathegorie(arraylist.get(position));
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void rempliArrayList(){
        arraylist.add("");
        arraylist.add("Aucune cathégorie");
        arraylist.add("Sport");
        arraylist.add("Hobby");
        arraylist.add("Ecole");
        arraylist.add("Electronique");
        arraylist.add("Musique");
        arraylist.add("Vetement");

    }

}
