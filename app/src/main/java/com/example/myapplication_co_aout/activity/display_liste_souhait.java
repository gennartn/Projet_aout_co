package com.example.myapplication_co_aout.activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.myapplication_co_aout.R;
import com.example.myapplication_co_aout.Sql.Database_list_souhait;
import com.example.myapplication_co_aout.model.ListeSouhaitAdapter;
import com.example.myapplication_co_aout.model.Liste_souhait_model;
import java.util.ArrayList;
public class display_liste_souhait extends AppCompatActivity {
    private Database_list_souhait db;
    private Login log;

    private ArrayList<Liste_souhait_model> souhaits;


    private static ListView listViewSouhait;
    private static Button creer;
    private static Button button_supprimer;
    private static Button button_modifier;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_view_souhait);
        db = new Database_list_souhait(this);


        listViewSouhait= (ListView) findViewById(R.id.listVieuw_souhait);
        creer = (Button) findViewById(R.id.creer);
        button_supprimer=(Button) findViewById((R.id.button_supprimer));
        button_modifier=(Button) findViewById(R.id.modifier);
        souhaits = new ArrayList<>();

        db.open();
        ListView();
        db.close();


        listViewSouhait.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Put_souhait_personne.class);

                String souhait = souhaits.get(position).getNom_liste();
                String personne = souhaits.get(position).getNom_personne();

                intent.putExtra("EXTRA_MESSAGE", souhait);
                intent.putExtra("EXTRA_MESSAGE1",personne);



                startActivity(intent);
            }
        });

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sauver_liste_de_souhait.class));
            }
        });

        button_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),supp_liste_de_souhait.class));
            }
        });

        button_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),mod_liste_de_souhait.class));
            }
        });

    }
    private void ListView(){
        Cursor cursor = db.getListSouhaits(Login.getUtilisateurPrincipale().getUsername());

        if(cursor.getCount()==0){
            Toast.makeText(display_liste_souhait.this, "Il n'y a pas encore de liste de souhait", Toast.LENGTH_LONG).show();
        }
        else{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                souhaits.add(new Liste_souhait_model(cursor.getString(1),cursor.getString(2)));
                cursor.moveToNext();
            }
        }
        ListeSouhaitAdapter adapter = new ListeSouhaitAdapter(this, R.layout.activity_list_view_souhait_adapter, souhaits);
        listViewSouhait.setAdapter(adapter);

    }



}


