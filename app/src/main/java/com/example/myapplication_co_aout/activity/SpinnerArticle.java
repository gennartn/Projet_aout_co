package com.example.myapplication_co_aout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.model.SpinnerAdapterArticle;


import java.util.ArrayList;


public class SpinnerArticle extends AppCompatActivity {

    TextView cathegorie;
    Spinner spinner;

    SpinnerAdapterArticle spinner_adapter;
    ArrayList<String> arraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);



        spinner = findViewById(R.id.spinner_id);
        cathegorie = findViewById(R.id.cathegorie);



        spinner_adapter = new SpinnerAdapterArticle(this, R.layout.activity_spinner, arraylist);
        spinner.setAdapter(spinner_adapter);
        rempliArrayList();
        spinner_adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(onItemSelectedListener);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cathegorie.setText(arraylist.get(position));
                if(cathegorie.getText().equals("")){
                    cathegorie.setText("Database");
                }
                else{
                    cathegorie.setText(arraylist.get(position));
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
