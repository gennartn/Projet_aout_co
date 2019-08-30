package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.activity.Login;
import com.example.myapplication_co_aout.model.Article;
import com.example.myapplication_co_aout.model.Liste_souhait_model;

import java.util.ArrayList;

public class Database_list_souhait  {
    private static final String TABLE_NAME = "liste_souhait";
    public static final String USERNAME="username";
    public static final String NOM_LISTE_SOUHAIT="nom_list_souhait";
    public static final String NOM_PERSONNE="nom_personne";
    public static final String CREATE_TABLE_LISTE_DE_SOUHAIT = "CREATE TABLE "+TABLE_NAME+
            " (\n" +
            " "+USERNAME+" TEXT ,"+
            " "+NOM_LISTE_SOUHAIT+" TEXT," +
            " "+NOM_PERSONNE+" TEXT" +
            ");";



    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;


    public Database_list_souhait(Context context)

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

    public long addListSouhait(Liste_souhait_model l_souhait, String utilisateur) {

        ContentValues values = new ContentValues();
        values.put(USERNAME, utilisateur);
        values.put(NOM_LISTE_SOUHAIT ,l_souhait.getNom_liste());
        values.put(NOM_PERSONNE,l_souhait.getNom_personne());


        return db.insert(TABLE_NAME,null,values);
    }

    public int modListSouhait(String souhait, String personne, String nouveau_souhait, String nouvelle_personne, String utilisateur) {

        ContentValues values = new ContentValues();
        values.put(USERNAME, utilisateur);
        values.put(NOM_LISTE_SOUHAIT, nouveau_souhait);
        values.put(NOM_PERSONNE, nouvelle_personne);

        String where = NOM_LISTE_SOUHAIT+" = ? AND "+ USERNAME +" = ? AND "+NOM_PERSONNE+" = ?";
        String[] whereArgs = {souhait, utilisateur,personne};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }


    public int supUnSouhait(String souhait, String personne, String utilisateur){


        String where1 = NOM_LISTE_SOUHAIT+" =? and "+NOM_PERSONNE+" =? and "+USERNAME+ " =?";
        String[] whereArgs1 = {souhait, personne, utilisateur};

        return db.delete(TABLE_NAME, where1, whereArgs1);

    }


    public Cursor getListSouhaits(String utilisateur) {
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USERNAME+ " = ?",new String[]{utilisateur});

    }


}
