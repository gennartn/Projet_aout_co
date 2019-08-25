package com.example.myapplication_co_aout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class New_login extends Login {

    private static Button creat_btn;
    private static EditText username;
    private static EditText password;
    private static EditText password_confirm;
    private static Button  return_1;
    public DatabaseHelper db;

    public void New_login(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        creat_btn = (Button) findViewById(R.id.button_creat);
        return_1 = (Button) findViewById(R.id.button_return);

        username = (EditText) findViewById(R.id.username);

        password = (EditText) findViewById(R.id.password_1);

        password_confirm = (EditText) findViewById(R.id.password_confirm);

        db = new DatabaseHelper(this);

        return_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_Login();
            }
        });

            creat_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CompareString(password.getText().toString(), password_confirm.getText().toString())) {
                        addData(username.getText().toString(), password.getText().toString());
                    }
                    else{
                        Toast.makeText(New_login.this, "la confirmation du password est fausse", Toast.LENGTH_LONG).show();
                        Toast.makeText(New_login.this, "retapez la confirmation du password", Toast.LENGTH_LONG).show();
                    }
                }
            });


    }
    public void Open_Login(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public boolean CompareString(String password, String password_confirm){
        if(password.equals(password_confirm)){
            return true;
        }
        else{
            return false;
        }

    }

    public DatabaseHelper getBbLogin(){
        return db;
    }

    public void addData(String username, String password) {
        boolean isInserted = db.insertData(username,password);
        if(isInserted == true){
            Toast.makeText(New_login.this, "Votre account est créé", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(New_login.this, "L'identifiant ou le mot de passe est déjà utilisé", Toast.LENGTH_LONG).show();
        }

    }
}
