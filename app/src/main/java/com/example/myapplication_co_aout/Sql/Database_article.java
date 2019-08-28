package com.example.myapplication_co_aout.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_co_aout.Sql.MySQLite;
import com.example.myapplication_co_aout.model.Article;

public class Database_article {

    private static final String TABLE_NAME = "article";
    public static final String NOM_ARTICLE="nom_article";
    public static final String PRIX="prix";
    public static final String CREATE_TABLE_ARTICLE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+NOM_ARTICLE+" TEXT," +
            " "+PRIX+" INTEGER" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public Database_article(Context context)
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

    public long addArticle(Article article) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(NOM_ARTICLE ,article.getNom_article());
        values.put(PRIX,article.getPrix_article());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modArticle(Article article) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(NOM_ARTICLE, article.getNom_article());
        values.put(PRIX, article.getPrix_article());

        String where = NOM_ARTICLE+" = ?";
        String[] whereArgs = {article.getNom_article()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supArticle(Article article) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = NOM_ARTICLE+" = ?";
        String[] whereArgs = {article.getNom_article()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Article getArticle(String nom_article) {
        // Retourne l'animal dont l'id est passé en paramètre

        Article a=new Article("",0);

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+NOM_ARTICLE+"="+nom_article, null);
        if (c.moveToFirst()) {
            a.setNom_Article(c.getString(c.getColumnIndex(NOM_ARTICLE)));
            a.setPrix_article(c.getInt(c.getColumnIndex(PRIX)));
            c.close();
        }

        return a;
    }

    public Cursor getArticles() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

}
