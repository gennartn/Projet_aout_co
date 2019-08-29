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

public class display_article extends AppCompatActivity {


    private static TextView nom_article;

    private static Button ajouter_prix;
    private static EditText edit_prix;
    private static TextView view_prix;

    private static Button ajouter_magasin;
    private static EditText edit_magasin;
    private static TextView view_magain;

    //private static SpinnerArticle spinner;

    private String prix;
    private String magasin;
    //private String cathegorie;

    private String article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            article = bundle.getString("ARTICLE");
        }
        nom_article = (TextView) findViewById(R.id.nom_article);

        nom_article.setText(article);

        ajouter_prix = (Button) findViewById(R.id.ajouter_prix);
        edit_prix = (EditText) findViewById(R.id.edit_prix);
        view_prix = (TextView) findViewById(R.id.view_prix);

        ajouter_magasin = (Button) findViewById(R.id.ajouter_magasin);
        edit_magasin = (EditText) findViewById(R.id.edit_magasin);
        view_magain = (TextView) findViewById(R.id.view_magasin);

        ajouter_prix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_prix.getText().toString();
                if(!temp.equals("")){
                    prix = temp;
                    view_prix.setText(prix);
                }
            }
        });

        ajouter_magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_magasin.getText().toString();
                if(!temp.equals("")){
                    magasin = temp;
                    view_magain.setText(magasin);
                }
            }
        });


/*
        SpinnerArticle spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.cathegorie_spin,R.layout.activity_display_article);
        adapter.setDropDownViewResource(R.layout.activity_display_article);
        spinner.setAdapter(adapter);

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cathegorie = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),cathegorie, Toast.LENGTH_SHORT).show();
            }
        });*/


    }

}
