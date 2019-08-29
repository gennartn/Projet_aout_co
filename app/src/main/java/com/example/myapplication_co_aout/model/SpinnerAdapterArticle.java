package com.example.myapplication_co_aout.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class SpinnerAdapterArticle extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> arraylist;

    public SpinnerAdapterArticle( Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);

        this.context=context;
        this.arraylist=objects;
    }

    @Override
    public int getCount(){
        return arraylist.size();
    }

    @Override
    public String getItem(int position){
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView textView = new TextView(context);

        textView.setText(arraylist.get(position));

        return textView;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        TextView textView = new TextView(context);
        textView.setText(arraylist.get(position));

        return textView;


    }


}
