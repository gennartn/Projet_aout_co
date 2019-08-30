package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.model.Liste_souhait_model;
import com.example.myapplication_co_aout.model.Personne;

public class Database_personne {
    private static final String TABLE_NAME = "personne";
    public static final String USERNAME="username";
    public static final String NOM_PERSONNE="nom_personne";
    public static final String NOM="nom";
    public static final String PRENOM="prenom";
    public static final String DATE="date";
    public static final String CATHEGORIE="cathegorie";
    public static final String CREATE_TABLE_PERSONNE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+USERNAME+" TEXT," +
            " "+NOM_PERSONNE+" TEXT," +
            " "+NOM+" TEXT," +
            " "+PRENOM+" TEXT," +
            " "+DATE+" TEXT," +
            " "+CATHEGORIE+" TEXT" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public Database_personne(Context context)
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

    public static String getTableName(){
        return TABLE_NAME;
    }

    public long addPersonne(Personne personne, String username, String nom_personne) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(USERNAME ,username);
        values.put(NOM_PERSONNE,nom_personne);
        values.put(NOM ,personne.getNom());
        values.put(PRENOM,personne.getPrenom());
        values.put(DATE ,personne.getDate());
        values.put(CATHEGORIE,personne.getCathegorie());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modPersonne(Personne personne, Personne nouvelle_personne, String username, String nom_personne) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(USERNAME ,username);
        values.put(NOM_PERSONNE,nom_personne);
        values.put(NOM ,nouvelle_personne.getNom());
        values.put(PRENOM,nouvelle_personne.getPrenom());
        values.put(DATE ,nouvelle_personne.getDate());
        values.put(CATHEGORIE,nouvelle_personne.getCathegorie());

        String where = NOM_PERSONNE+" = ? AND "+USERNAME+" = ? AND "+NOM+" = ? AND "+PRENOM+" = ? AND "+DATE+" = ? AND "+CATHEGORIE+ " = ?";
        String[] whereArgs = {nom_personne,username,personne.getNom(),personne.getPrenom(),personne.getDate(),personne.getCathegorie()};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }
    public int modNomPersonne(String username, String personne, String nouvelle_personne){
        ContentValues values = new ContentValues();
        values.put(USERNAME,username);
        values.put(NOM_PERSONNE,nouvelle_personne);

        String where = USERNAME+" = ? AND "+ NOM_PERSONNE+" = ?";
        String[] whereArgs = {username, personne};

        return db.update(TABLE_NAME,values, where, whereArgs);
    }

    public int supPersonne(String username, String nom_personne) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = NOM_PERSONNE+" = ? AND "+USERNAME+" = ?";
        String[] whereArgs = {username, nom_personne};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Personne getInfosPersonne(String username, String nom_personne) {

        Personne a=new Personne("","","","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+USERNAME+"= ? AND "+NOM_PERSONNE+" = ?" , new String[]{username,nom_personne});
        if(c.getCount()==0){
            return null;
        }
        if (c.moveToFirst()) {
            a.setNom(c.getString(c.getColumnIndex(NOM)));
            a.setPrenom(c.getString(c.getColumnIndex(PRENOM)));
            a.setDate(c.getString(c.getColumnIndex(DATE)));
            a.setCathegorie(c.getString(c.getColumnIndex(CATHEGORIE)));
            c.close();
        }

        return a;
    }

    public boolean personnePresent(String username, String nom_personne){
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+USERNAME+"= ? AND "+NOM_PERSONNE+" = ?", new String[]{username,nom_personne});
        if(c.getCount()==0){
            return false;
        }
        return true;
    }

    public Cursor getPersonnes() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
