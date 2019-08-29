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
    public static final String NOM_SOUHAIT="nom_souhait";
    public static final String NOM_ARTICLE="nom_article";
    public static final String PRIX="prix";
    public static final String MAGASIN="magasin";
    public static final String CATHEGORIE="cathegorie";

    public static final String CREATE_TABLE_ARTICLE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+USERNAME+" TEXT," +
            " "+NOM_SOUHAIT+" TEXT," +
            " "+NOM_ARTICLE+" TEXT," +
            " "+PRIX+" TEXT," +
            " "+MAGASIN+" TEXT," +
            " "+CATHEGORIE+" TEXT" +
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

    public long addInfosArticle(Article article, String username, String nom_souhait) {

        ContentValues values = new ContentValues();
        values.put(USERNAME ,username);
        values.put(NOM_SOUHAIT ,nom_souhait);
        values.put(NOM_ARTICLE ,article.getNom_article());
        values.put(PRIX,article.getPrix_article());
        values.put(MAGASIN,article.getNom_magasin());
        values.put(CATHEGORIE,article.getNom_cathegorie());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modInfosArticle(Article article,Article nouveau_article, String utilisateur, String nom_souhait) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(USERNAME ,utilisateur);
        values.put(NOM_SOUHAIT ,nom_souhait);
        values.put(NOM_ARTICLE ,nouveau_article.getNom_article());
        values.put(PRIX,nouveau_article.getPrix_article());
        values.put(MAGASIN,nouveau_article.getNom_magasin());
        values.put(CATHEGORIE,nouveau_article.getNom_cathegorie());

        String where = NOM_ARTICLE+" = ? AND "+USERNAME+" = ? AND "+PRIX+" = ? AND "+MAGASIN+" = ? AND "+CATHEGORIE+" = ? AND "+NOM_SOUHAIT+" = ?";
        String[] whereArgs = {article.getNom_article(), utilisateur, article.getPrix_article(), article.getNom_magasin(), article.getNom_cathegorie(), nom_souhait};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supInfosArticle(Article article, String utilisateur, String nom_souhait) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = NOM_ARTICLE+" = ? AND "+USERNAME+ " = ? AND "+NOM_SOUHAIT+" = ?";
        String[] whereArgs = {article.getNom_article(), utilisateur, nom_souhait};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Article getArticle(String nom_article, String username, String nom_souhait) {

        Article a=new Article("","","","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+NOM_ARTICLE+"= ? AND "+USERNAME+" = ? AND "+NOM_SOUHAIT+" = ?", new String[]{nom_article,username,nom_souhait});
        if (c.moveToFirst()) {
            a.setNom_Article(c.getString(c.getColumnIndex(NOM_ARTICLE)));
            a.setPrix_article(c.getString(c.getColumnIndex(PRIX)));
            a.setNom_magasin(c.getString(c.getColumnIndex(MAGASIN)));
            a.setNom_cathegorie(c.getString(c.getColumnIndex(CATHEGORIE)));
            c.close();
        }

        return a;
    }

    public Cursor getInfosArticle() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE "+USERNAME+" = ?", null);
    }

}
