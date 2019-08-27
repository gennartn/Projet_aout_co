package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_list_souhait;
import com.example.myapplication_co_aout.model.Liste_souhait_model;

public class sauver_liste_de_souhait extends AppCompatActivity {

    private static EditText nom_list_souhait;
    private static EditText destinataire;
    private static Button button_save;
    private static Button retour;

    private Database_list_souhait db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauver_liste_de_souhait);

        nom_list_souhait = (EditText) findViewById(R.id.nom_liste_souhait);
        destinataire = (EditText) findViewById(R.id.destinataire);

        button_save = (Button) findViewById(R.id.button_save);
        retour = (Button) findViewById(R.id.retour);

        db = new Database_list_souhait(getApplicationContext());


        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l_souhait = nom_list_souhait.getText().toString();
                String l_destinataire = destinataire.getText().toString();
                db.open();
                if(!l_souhait.equals("") && !l_destinataire.equals("") ){
                    Liste_souhait_model liste = new Liste_souhait_model(l_souhait, l_destinataire);
                    if(db.addListSouhait(liste)!=-1){
                        Toast.makeText(sauver_liste_de_souhait.this, "Vorte liste de souhait a été créé", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(sauver_liste_de_souhait.this, "Le nom de la liste existe déjà", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(sauver_liste_de_souhait.this, "le nom de la liste ou le nom du destinataire est manquant", Toast.LENGTH_LONG).show();
                }
                db.close();
                destinataire.setText("");
                nom_list_souhait.setText("");
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


