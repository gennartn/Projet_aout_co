package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.Sql.MySQLite;
import com.example.myapplication_co_aout.model.Article;

public class Database_article {

    private static final String TABLE_NAME = "article";

    public static final String USERNAME="username";
    public static final String NOM_LISTE_SOUHAIT="nom_liste_souhait";
    public static final String ARTICLE="article";
    public static final String PRIX="prix";
    public static final String MAGASIN="magasin";
    public static final String CATHEGORIE="cathegorie";

    public static final String CREATE_TABLE_ARTICLE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+USERNAME+" TEXT," +
            " "+NOM_LISTE_SOUHAIT+" TEXT," +
            " "+ARTICLE+" TEXT," +
            " "+PRIX+" TEXT," +
            " "+MAGASIN+" TEXT," +
            " "+CATHEGORIE+" TEXT" +
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;


    public Database_article(Context context)
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

    public long addInfosArticle(Article article, String username, String nom_souhait) {

        ContentValues values = new ContentValues();
        values.put(USERNAME ,username);
        values.put(NOM_LISTE_SOUHAIT ,nom_souhait);
        values.put(ARTICLE ,article.getNom_article());
        values.put(PRIX,article.getPrix_article());
        values.put(MAGASIN,article.getNom_magasin());
        values.put(CATHEGORIE,article.getNom_cathegorie());

        return db.insert(TABLE_NAME,null,values);
    }

    public int modInfosArticle(Article article,Article nouveau_article, String utilisateur, String nom_souhait) {

        ContentValues values = new ContentValues();
        values.put(USERNAME ,utilisateur);
        values.put(NOM_LISTE_SOUHAIT ,nom_souhait);
        values.put(ARTICLE ,nouveau_article.getNom_article());
        values.put(PRIX,nouveau_article.getPrix_article());
        values.put(MAGASIN,nouveau_article.getNom_magasin());
        values.put(CATHEGORIE,nouveau_article.getNom_cathegorie());

        String where = ARTICLE+" = ? AND "+USERNAME+" = ? AND "+PRIX+" = ? AND "+MAGASIN+" = ? AND "+CATHEGORIE+" = ? AND "+NOM_LISTE_SOUHAIT+" = ?";
        String[] whereArgs = {article.getNom_article(), utilisateur, article.getPrix_article(), article.getNom_magasin(), article.getNom_cathegorie(), nom_souhait};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int modNomSouhait(String username, String souhait, String nouveau_souhait){
        ContentValues values = new ContentValues();
        values.put(USERNAME,username);
        values.put(NOM_LISTE_SOUHAIT,nouveau_souhait);

        String where = USERNAME+" = ? AND "+ NOM_LISTE_SOUHAIT+" = ?";
        String[] whereArgs = {username, souhait};

        return db.update(TABLE_NAME,values, where, whereArgs);
    }

    public int modNomArticle(String username, String souhait, String article, String nouveau_article){
        ContentValues values = new ContentValues();
        values.put(USERNAME,username);
        values.put(NOM_LISTE_SOUHAIT,souhait);
        values.put(ARTICLE,nouveau_article);

        String where = USERNAME+" = ? AND "+ NOM_LISTE_SOUHAIT+" = ? AND "+ARTICLE+" = ?";
        String[] whereArgs = {username, souhait,article};

        return db.update(TABLE_NAME,values, where, whereArgs);
    }

    public int supInfosUnArticle(String article, String utilisateur, String nom_souhait) {

        String where = ARTICLE+" = ? AND "+USERNAME+ " = ? AND "+NOM_LISTE_SOUHAIT+" = ?";
        String[] whereArgs = {article, utilisateur, nom_souhait};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public int suppInfosArticle(String souhait, String utilisateur){
        String where = NOM_LISTE_SOUHAIT+" =? and "+USERNAME+ " =?";
        String[] whereArgs = {souhait, utilisateur};

        return db.delete(TABLE_NAME, where, whereArgs);
    }


    public Article getArticle(String nom_article, String username, String nom_souhait) {

        Article a=new Article("","","","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ARTICLE+"= ? AND "+USERNAME+" = ? AND "+NOM_LISTE_SOUHAIT+" = ?", new String[]{nom_article,username,nom_souhait});
        if(c.getCount()==0){
            return null;
        }
        if (c.moveToFirst()) {
            a.setNom_Article(c.getString(c.getColumnIndex(ARTICLE)));
            a.setPrix_article(c.getString(c.getColumnIndex(PRIX)));
            a.setNom_magasin(c.getString(c.getColumnIndex(MAGASIN)));
            a.setNom_cathegorie(c.getString(c.getColumnIndex(CATHEGORIE)));
            c.close();
        }

        return a;
    }

    public boolean articlePresent(String nom_article, String nom_souhait, String username){
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ARTICLE+"= ? AND "+USERNAME+" = ? AND "+NOM_LISTE_SOUHAIT+" = ?", new String[]{nom_article,username,nom_souhait});
        if(c.getCount()==0){
            return false;
        }
        return true;
    }


}
