package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication_co_aout.R;

public class Put_souhait_personne extends AppCompatActivity {


    private static Button button_souhait;
    private static Button button_personne;
    private static Button retour;

    private static String souhait1;
    private static String personne1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_souhait_personne);


        button_souhait = (Button) findViewById(R.id.button);
        button_personne = (Button) findViewById(R.id.button2);
        retour = (Button) findViewById(R.id.retour);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            souhait1 = bundle.getString("SOUHAIT");
            personne1 = bundle.getString("PERSONNE");
        }






         button_souhait.setText(souhait1);
         button_personne.setText(personne1);


        button_souhait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),display_liste_article.class);
                startActivity(intent);
            }
        });
        button_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),infos_personne.class);
                startActivity(intent1);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),display_liste_souhait.class));
            }
        });

    }
    public static String getSouhait1(){
        return souhait1;
    }
    public static String getPersonne1(){
        return personne1;
    }
}
