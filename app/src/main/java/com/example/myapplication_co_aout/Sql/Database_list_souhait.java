package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.model.Liste_souhait_model;

import java.util.ArrayList;

public class Database_list_souhait  {
    private static final String TABLE_NAME = "liste_souhait";
    public static final String NOM_LISTE_SOUHAIT="nom_list_souhait";
    public static final String NOM_PERSONNE="personne";
    public static final String CREATE_TABLE_LISTE_DE_SOUHAIT = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+NOM_LISTE_SOUHAIT+" TEXT primary key," +
            " "+NOM_PERSONNE+" TEXT" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public Database_list_souhait(Context context)

    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addListSouhait(Liste_souhait_model l_souhait) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(NOM_LISTE_SOUHAIT ,l_souhait.getNom_liste());
        values.put(NOM_PERSONNE,l_souhait.getNom_personne());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modListSouhait(Liste_souhait_model l_souhait) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(NOM_LISTE_SOUHAIT, l_souhait.getNom_liste());
        values.put(NOM_PERSONNE, l_souhait.getNom_personne());

        String where = NOM_LISTE_SOUHAIT+" = ?";
        String[] whereArgs = {l_souhait.getNom_liste()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supListSouhait(Liste_souhait_model l_souhait) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = NOM_LISTE_SOUHAIT+" = ?";
        String[] whereArgs = {l_souhait.getNom_liste()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Liste_souhait_model getListSouhait(String nom_list_souhait) {
        // Retourne l'animal dont l'id est passé en paramètre

        Liste_souhait_model a=new Liste_souhait_model("","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+NOM_LISTE_SOUHAIT+"="+nom_list_souhait, null);
        if (c.moveToFirst()) {
            a.setNom_list(c.getString(c.getColumnIndex(NOM_LISTE_SOUHAIT)));
            a.setNom_personne(c.getString(c.getColumnIndex(NOM_PERSONNE)));
            c.close();
        }

        return a;
    }
    public ArrayList<String> takeDataListeDeSouhait(){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
        }
        else{
            list.add("aucune liste n'a été créé");
        }
        return list;
    }

    public Cursor getListSouhaits() {
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    /*public void getInfos(){
        Cursor cursor = getListSouhaits();
        String nom_liste_souhait, nom_personne;
        souhaitAdapter = new Liste_souhaitAdapter(context);
        while(cursor.moveToNext()){
            nom_liste_souhait= cursor.getString(cursor.getColumnIndex(NOM_LISTE_SOUHAIT));
            nom_personne = cursor.getString(cursor.getColumnIndex(NOM_PERSONNE));
            Liste_souhait_model l_souhait = new Liste_souhait_model(nom_liste_souhait,nom_personne);
            //publishProgress(l_souhait);
        }

    }*/

}
