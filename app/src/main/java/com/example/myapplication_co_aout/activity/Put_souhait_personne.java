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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_souhait_personne);


        button_souhait = (Button) findViewById(R.id.button);
        button_personne = (Button) findViewById(R.id.button2);
        retour = (Button) findViewById(R.id.retour);


        Intent intent = getIntent();

        final String souhait1 = intent.getStringExtra("EXTRA_MESSAGE");
        final String personne1 = intent.getStringExtra("EXTRA_MESSAGE1");

        button_souhait.setText(souhait1);
        button_personne.setText(personne1);


        button_souhait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),display_liste_article.class);
                intent.putExtra("EXTRA", souhait1);
                startActivity(intent);
            }
        });
        button_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),infos_personne.class);
                intent1.putExtra("ETRAT1",personne1);
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
}
