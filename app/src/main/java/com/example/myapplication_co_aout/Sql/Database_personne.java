package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.model.Liste_souhait_model;
import com.example.myapplication_co_aout.model.Personne;

public class Database_personne {
    private static final String TABLE_NAME = "personne";
    public static final String PSEUDO="pseudo";
    public static final String NOM_PERSONNE="nom_personne";
    public static final String CREATE_TABLE_PERSONNE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+NOM_PERSONNE+" TEXT primary key," +
            " "+PSEUDO+" TEXT" +
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

    public long addPersonne(Personne personne) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(NOM_PERSONNE ,personne.getNom_Personne());
        values.put(PSEUDO,personne.getPseudo());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modPersonne(Personne personne) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(NOM_PERSONNE, personne.getNom_Personne());
        values.put(PSEUDO, personne.getPseudo());

        String where = NOM_PERSONNE+" = ?";
        String[] whereArgs = {personne.getNom_Personne()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supPersonne(Personne personne) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = NOM_PERSONNE+" = ?";
        String[] whereArgs = {personne.getNom_Personne()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Personne getPersonne(String nom_personne) {
        // Retourne l'animal dont l'id est passé en paramètre

        Personne a=new Personne("","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+NOM_PERSONNE+"="+nom_personne, null);
        if (c.moveToFirst()) {
            a.setNom_personne(c.getString(c.getColumnIndex(NOM_PERSONNE)));
            a.setPseudo(c.getString(c.getColumnIndex(PSEUDO)));
            c.close();
        }

        return a;
    }

    public Cursor getPersonnes() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
