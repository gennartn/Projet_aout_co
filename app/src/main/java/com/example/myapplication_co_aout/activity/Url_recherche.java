package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication_co_aout.R;

public class Url_recherche extends AppCompatActivity {

    private static Button button_web;
    private static Button retour;
    private static EditText edit_web;
    private static WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_recherche);

        button_web = findViewById(R.id.button_web);
        edit_web = findViewById(R.id.edit_web);
        browser = findViewById(R.id.webView_image);

        button_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edit_web.getText().toString();
                browser.getSettings().setLoadsImagesAutomatically(true);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                browser.loadUrl(url);
            }
        });

        retour = (Button) findViewById(R.id.retour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Put_souhait_personne.class));
            }
        });

    }

}
