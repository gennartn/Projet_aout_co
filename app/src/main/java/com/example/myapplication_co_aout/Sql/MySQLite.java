package com.example.myapplication_co_aout.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication_co_aout.model.Article;

public class MySQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static MySQLite Instance;

    public static synchronized MySQLite getInstance(Context context) {
        if (Instance == null) {
            Instance = new MySQLite(context);
        }
        return Instance;
    }

    private MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la base de données
        // on exécute ici les requêtes de création des tables
        sqLiteDatabase.execSQL(Database_log.CREATE_TABLE_LOG);
        sqLiteDatabase.execSQL(Database_article.CREATE_TABLE_ARTICLE); // création table "animal"
        sqLiteDatabase.execSQL(Database_list_souhait.CREATE_TABLE_LISTE_DE_SOUHAIT);
        sqLiteDatabase.execSQL(Database_personne.CREATE_TABLE_PERSONNE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Mise à jour de la base de données
        // méthode appelée sur incrémentation de DATABASE_VERSION
        // on peut faire ce qu'on veut ici, comme recréer la base :
        onCreate(sqLiteDatabase);
    }

}