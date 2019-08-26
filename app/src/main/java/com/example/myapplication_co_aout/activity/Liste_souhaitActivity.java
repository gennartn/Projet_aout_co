package com.example.myapplication_co_aout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.model.CustomAdapter;

public class Liste_souhaitActivity extends AppCompatActivity {
    private static (Button) button_creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_souhait);

        button_creat = (Button) findViewById(R.id.button_creer);
        ArrayList<String> table_liste_de_souhait = db.takeDataListeDeSouhait();
        String [] temp = new String[table_liste_de_souhait.size()];
        String [] liste_souhait = table_liste_de_souhait.toArray(temp);

        String[] foods = {"Bacon", "Ham", "Tuna", "Candy", "HeatBall", "Patota","Bonjour", "Aurevoir", "Salut","Hello"};
        ListAdapter buckysAdapter = new CustomAdapter(this, foods);
        ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String food = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(Liste_souhaitActivity.this, food, Toast.LENGTH_LONG).show();
            }
        });
    }
}
