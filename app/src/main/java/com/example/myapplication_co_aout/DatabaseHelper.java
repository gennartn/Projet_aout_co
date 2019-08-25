package com.example.myapplication_co_aout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Login.db";
    public static final String TABLE_NAME = "Login_Table";
    public static final String COL_1= "USER_NAME";
    public static final String COL_2 = "PASSWORD";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(USER_NAME TEXT PRIMARY KEY, PASSWORD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean checkData(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT USERNAME, PASSWORD from Login_Table where USERNAME = '"+userName+" ' AND PASSWORD = '"+password+"'", null);
        if(res.getCount()==0){
            return false;
        }
        else{
            return true;
        }

    }
}
