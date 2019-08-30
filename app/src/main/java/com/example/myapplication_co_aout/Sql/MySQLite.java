package com.example.myapplication_co_aout.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database88.db";
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

        sqLiteDatabase.execSQL(Database_log.CREATE_TABLE_LOG);
        sqLiteDatabase.execSQL(Database_article.CREATE_TABLE_ARTICLE);
        sqLiteDatabase.execSQL(Database_list_souhait.CREATE_TABLE_LISTE_DE_SOUHAIT);
        sqLiteDatabase.execSQL(Database_personne.CREATE_TABLE_PERSONNE);
        sqLiteDatabase.execSQL(Database_list_article.CREATE_TABLE_LISTE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database_list_article.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database_list_souhait.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database_log.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database_personne.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database_article.getTableName());

        onCreate(sqLiteDatabase);
    }


}