package com.example.myapplication_co_aout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_log;
import com.example.myapplication_co_aout.model.Log;
import com.example.myapplication_co_aout.model.Utilisateur;


public class Login extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    private static Button creat_new_account;
    private Database_log db;
    private static Utilisateur utilisateur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username_1);
        password = (EditText) findViewById(R.id.password_1);
        login_btn= (Button) findViewById(R.id.button_login);
        creat_new_account = (Button) findViewById(R.id.button_creat_new_account);



        db = new Database_log(this);




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

    public void Check_Data_Base(String username1, String password1){
        Log log = new Log(username1, password1);
        db.open();
        if(db == null){
            db.close();
            Toast.makeText(Login.this, "Aucun compte n'a été créé", Toast.LENGTH_LONG).show();
        }
        else{
            if(db.checkData(log)){
                Toast.makeText(Login.this, "Votre code est correct", Toast.LENGTH_LONG).show();
                db.close();

                startActivity(new Intent(getApplicationContext(), display_liste_souhait.class));
                utilisateur = new Utilisateur(username1, password1);
                username.setText("");
                password.setText("");
            }
            else{
                db.close();
                Toast.makeText(Login.this, "Votre code est incorrect", Toast.LENGTH_LONG).show();
            }
        }


    }
    public static Utilisateur getUtilisateurPrincipale(){
        return utilisateur;
    }

}
