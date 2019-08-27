package com.example.myapplication_co_aout.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication_co_aout.R;

import java.util.ArrayList;
import java.util.List;

public class ListeSouhaitAdapter extends ArrayAdapter<Liste_souhait_model> {

    private Context mContext;
    int ressource;


    public ListeSouhaitAdapter( Context context, int resource, ArrayList<Liste_souhait_model> objects) {
        super(context, resource, objects);
        mContext = context;
        ressource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String nom_souhaits = getItem(position).getNom_liste();
        String nom_personne = getItem(position).getNom_personne();

        Liste_souhait_model souhait = new Liste_souhait_model(nom_souhaits,nom_personne);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(ressource,parent,false);

        TextView  nom_l_s = (TextView) convertView.findViewById(R.id.textView1);
        TextView nom_p = (TextView) convertView.findViewById(R.id.textView2);

        nom_l_s.setText(nom_souhaits);
        nom_p.setText(nom_personne);

        return convertView;

    }

}
