package com.example.myapplication_co_aout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] foods){
        super(context,R.layout.activity_custom_adapter ,foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView= buckysInflater.inflate(R.layout.activity_custom_adapter,parent,false);


        String singlefoodItem = getItem(position);
        TextView buckyText = (TextView) customView.findViewById(R.id.buckysText);

        buckyText.setText(singlefoodItem);

        return customView;

    }


}

