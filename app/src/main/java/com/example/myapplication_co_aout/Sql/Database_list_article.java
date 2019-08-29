package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.model.Liste_article;
import com.example.myapplication_co_aout.model.Liste_souhait_model;

import java.util.ArrayList;

public class Database_list_article {
    private static final String TABLE_NAME = "liste_article";
    public static final String USERNAME = "username";
    public static final String NOM_LISTE_SOUHAIT="nom_list_souhait";
    public static final String ARTICLE="article";
    public static final String CREATE_TABLE_LISTE_ARTICLE= "CREATE TABLE "+TABLE_NAME+
            " (\n" +
            " "+USERNAME+ " TEXT,"+
            " "+NOM_LISTE_SOUHAIT+" TEXT,"+
            " "+ARTICLE+" TEXT" +
            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;


    public Database_list_article(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }
    public static String getTableName(){
        return TABLE_NAME;
    }

    public Cursor getListArticle(String nom_liste_souhait, String utilisateur) {
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ NOM_LISTE_SOUHAIT+ " = ? AND "+USERNAME+" = ?",new String[]{nom_liste_souhait, utilisateur});

    }
    public long addListArticle(Liste_article article, String nom_liste_souhait, String utilisateur) {

        ContentValues values = new ContentValues();
        values.put(USERNAME,utilisateur);
        values.put(NOM_LISTE_SOUHAIT, nom_liste_souhait);
        values.put(ARTICLE ,article.getNom_liste());


        return db.insert(TABLE_NAME,null,values);
    }

    public int modListArticle(String article, String nouvelle_article, String nom_souhait, String utilisateur) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(USERNAME, utilisateur);
        values.put(NOM_LISTE_SOUHAIT, nom_souhait);
        values.put(ARTICLE, nouvelle_article);

        String where = NOM_LISTE_SOUHAIT+" = ? AND "+ USERNAME +" = ? AND "+ARTICLE+" = ?";
        String[] whereArgs = {nom_souhait, utilisateur,article};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supUnArticle(String article, String nom_souhait, String utilisateur){

        //suprimer les information de l'article
        //supInfosArticle(article,nom_souhait,utilisateur);

        String where = NOM_LISTE_SOUHAIT+" =? and "+ARTICLE+" =? and "+USERNAME+ " =?";
        String[] whereArgs = {nom_souhait, article, utilisateur};

        return db.delete(TABLE_NAME, where, whereArgs);

    }
    public int suppArticle(String souhait, String utilisateur){
        String where = NOM_LISTE_SOUHAIT+" =? and "+USERNAME+ " =?";
        String[] whereArgs = {souhait, utilisateur};
        //Database_list_article db_A = new Database_list_article(this);


        return db.delete(TABLE_NAME, where, whereArgs);
    }


}
