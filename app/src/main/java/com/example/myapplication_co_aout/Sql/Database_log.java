package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.model.Log;

public class Database_log {
    public static final String TABLE_NAME = "Login_Table";
    public static final String COL_1= "USER_NAME";
    public static final String COL_2 = "PASSWORD";

    public static final String CREATE_TABLE_LOG = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+COL_1+" TEXT primary key," +
            " "+COL_2+" TEXT" +
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;


    public Database_log(Context context)
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





    public boolean insertData(Log log){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, log.getUsername());
        contentValues.put(COL_2, log.getPassword());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkData(Log log) {

        Cursor res1 = db.rawQuery("SELECT * FROM Login_Table WHERE USER_NAME = ? AND PASSWORD = ?", new String[]{log.getUsername(), log.getPassword()});
        if(res1.getCount()==0){
            return false;
        }
        else{
            return true;
        }

    }
}
