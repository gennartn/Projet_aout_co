package com.example.myapplication_co_aout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Liste_souhait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_souhait);

        String[] foods = {"Bacon", "Ham", "Tuna", "Candy", "HeatBall", "Patota","Bonjour", "Aurevoir", "Salut","Hello"};
        ListAdapter buckysAdapter = new CustomAdapter(this, foods);
        ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String food = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(Liste_souhait.this, food, Toast.LENGTH_LONG).show();
            }
        });
    }
}
