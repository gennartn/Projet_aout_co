package com.example.myapplication_co_aout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    private static Button creat_new_account;
    public DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username_1);
        password = (EditText) findViewById(R.id.password_1);
        login_btn= (Button) findViewById(R.id.button_login);
        creat_new_account = (Button) findViewById(R.id.button_creat_new_account);



        db = new DatabaseHelper(getApplicationContext());


        creat_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_New_Login();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_Data_Base(username.getText().toString(),password.getText().toString());

            }
        });

    }

    public void Open_New_Login(){
        Intent intent = new Intent(this, New_login.class);
        startActivity(intent);
    }

    public void Check_Data_Base(String username, String password){


        if(db == null){
            Toast.makeText(Login.this, "Aucun compte n'a été créé", Toast.LENGTH_LONG).show();
        }
        else{
            if(db.checkData(username,password)){
                Toast.makeText(Login.this, "Votre code est correct", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Login.this, "Votre code est incorrect", Toast.LENGTH_LONG).show();
            }
        }


    }

}
