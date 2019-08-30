package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_personne;
import com.example.myapplication_co_aout.model.Personne;
import com.example.myapplication_co_aout.model.SpinnerAdapterArticle;
import com.example.myapplication_co_aout.model.SpinnerAdapterPersonne;

import java.util.ArrayList;

public class display_personne extends AppCompatActivity {

    private static Button ajouter_nom;
    private static EditText edit_nom;
    private static TextView view_nom;

    private static Button ajouter_prenom;
    private static EditText edit_prenom;
    private static TextView view_prenom;

    private static Button ajouter_date;
    private static EditText edit_date;
    private static TextView view_date;

    private static TextView view_username;

    private static TextView view_spinner;
    private static Spinner spinner2;

    private SpinnerAdapterPersonne spinner_adapter;
    private ArrayList<String> arraylist = new ArrayList<>();

    private Personne personne;
    private Personne nouvelle_personne;

    private String username;

    private Database_personne db;

    private static Button sauver;
    private static Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_personne);

        ajouter_nom = (Button) findViewById(R.id.ajouter_nom);
        edit_nom = (EditText) findViewById(R.id.edit_nom);
        view_nom = (TextView) findViewById(R.id.view_nom);

        ajouter_prenom = (Button) findViewById(R.id.ajouter_prenom);
        edit_prenom = (EditText) findViewById(R.id.edit_prenom);
        view_prenom = (TextView) findViewById(R.id.view_prenom);

        ajouter_date = (Button) findViewById(R.id.ajouter_date);
        edit_date = (EditText) findViewById(R.id.edit_date);
        view_date = (TextView) findViewById(R.id.view_date);

        view_username = (TextView) findViewById(R.id.view_username);

        spinner2 = findViewById(R.id.spinner2);
        view_spinner = findViewById(R.id.view_spinner);

        sauver = (Button) findViewById(R.id.sauver);
        retour = (Button) findViewById(R.id.retour);

        username = Put_souhait_personne.getPersonne1();
        view_username.setText(username);

        nouvelle_personne = new Personne("","","","");

        db = new Database_personne(getApplicationContext());
        db.open();

        personne = db.getInfosPersonne(Login.getUtilisateurPrincipale().getUsername(),Put_souhait_personne.getPersonne1());
        if(personne!=null){
            view_nom.setText(personne.getNom());
            view_prenom.setText(personne.getPrenom());
            view_date.setText(personne.getDate());
        }
        db.close();

        ajouter_nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_nom.getText().toString();
                if(!temp.equals("") && !temp.equals(" Nom:")){
                    nouvelle_personne.setNom(temp);
                    view_nom.setText(" "+nouvelle_personne.getNom());
                    edit_nom.setText(" Nom:");
                }
                else{
                    Toast.makeText(display_personne.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                    edit_nom.setText(" Nom:");
                }

            }
        });

        ajouter_prenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_prenom.getText().toString();
                if(!temp.equals("") && !temp.equals(" Prenom:")){
                    nouvelle_personne.setPrenom(temp);
                    view_prenom.setText(" "+nouvelle_personne.getPrenom());
                    edit_prenom.setText(" Prenom:");
                }
                else{
                    Toast.makeText(display_personne.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                    edit_prenom.setText(" Prenom:");
                }

            }
        });

        ajouter_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = edit_date.getText().toString();
                if(!temp.equals("") && !temp.equals(" Date de naissance:")){
                    nouvelle_personne.setDate(temp);
                    view_date.setText(" "+nouvelle_personne.getDate());
                    edit_date.setText(" Date de naissance:");
                }
                else{
                    Toast.makeText(display_personne.this, "Vous n'avez rien noté", Toast.LENGTH_LONG).show();
                    edit_date.setText(" Date de naissance:");
                }

            }
        });

        spinner_adapter = new SpinnerAdapterPersonne(this, R.layout.activity_display_article, arraylist);
        spinner2.setAdapter(spinner_adapter);
        rempliArrayList();
        spinner_adapter.notifyDataSetChanged();
        spinner2.setOnItemSelectedListener(onItemSelectedListener);

        sauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                String nom_personne = Put_souhait_personne.getPersonne1();
                String username = Login.getUtilisateurPrincipale().getUsername();
                if(personne!=null){
                    if(nouvelle_personne.getNom().equals("")){
                        nouvelle_personne.setNom(personne.getNom());
                    }
                    if(nouvelle_personne.getPrenom().equals("")){
                        nouvelle_personne.setPrenom(personne.getPrenom());
                    }
                    if(nouvelle_personne.getDate().equals("")){
                        nouvelle_personne.setDate(personne.getDate());
                    }
                    if(nouvelle_personne.getCathegorie().equals("")){
                        nouvelle_personne.setCathegorie(personne.getCathegorie());
                    }

                }

                if(db.personnePresent(username,nom_personne)){

                    db.modPersonne(personne,nouvelle_personne, username, nom_personne);
                }
                else{
                    db.addPersonne(nouvelle_personne,username,nom_personne);
                }
                Toast.makeText(display_personne.this, "Vos informations ont été sauvées", Toast.LENGTH_LONG).show();
                db.close();
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Put_souhait_personne.class));
            }
        });


    }
    AdapterView.OnItemSelectedListener onItemSelectedListener= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            view_spinner.setText(arraylist.get(position));
            if(view_spinner.getText().equals("")){
                if(personne!=null){
                    view_spinner.setText(personne.getCathegorie());

                }
                else{
                    view_spinner.setText("Cathégorie:");
                }

            }
            else{
                view_spinner.setText(arraylist.get(position));
                nouvelle_personne.setCathegorie(arraylist.get(position));
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void rempliArrayList(){
        arraylist.add("");
        arraylist.add("Famille");
        arraylist.add("Ami");
        arraylist.add("Collegue");
        arraylist.add("Autre");
    }
}
