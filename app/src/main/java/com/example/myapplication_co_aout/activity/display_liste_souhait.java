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



    private static ListView listViewSouhait;
    private static Button creer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_view_souhait);
        db = new Database_list_souhait(this);


        listViewSouhait= (ListView) findViewById(R.id.listVieuw_souhait);
        creer = (Button) findViewById(R.id.creer);


        db.open();
        ListView();
        db.close();

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sauver_liste_de_souhait.class));
            }
        });
        listViewSouhait.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listViewSouhait.getItemAtPosition(position).toString();
                Toast.makeText(display_liste_souhait.this, text, Toast.LENGTH_LONG).show();
            }
        });

    }
    private void ListView(){
        Cursor cursor = db.getListSouhaits();
        ArrayList<Liste_souhait_model> souhaits = new ArrayList<>();
        if(cursor.getCount()==0){
            Toast.makeText(display_liste_souhait.this, "Il n'y a pas encore de liste de souhait", Toast.LENGTH_LONG).show();
        }
        else{

            while(cursor.moveToNext()){
                souhaits.add(new Liste_souhait_model(cursor.getString(0),cursor.getString(1)));
            }
        }

        ListeSouhaitAdapter adapter = new ListeSouhaitAdapter(this, R.layout.activity_list_view_souhait_adapter, souhaits);
        listViewSouhait.setAdapter(adapter);

    }

}


