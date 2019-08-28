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

public class ListeArticleAdapter extends ArrayAdapter<Liste_article> {
    private Context mContext;
    private int ressource;


    public ListeArticleAdapter(Context context, int resource, ArrayList<Liste_article> objects) {
       super(context,resource, objects);
        mContext = context;
        ressource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String nom_article = getItem(position).getNom_liste();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(ressource,parent,false);

        TextView nom_a = (TextView) convertView.findViewById(R.id.textViewAdapter);

        nom_a.setText(nom_article);

        return convertView;

    }


}
