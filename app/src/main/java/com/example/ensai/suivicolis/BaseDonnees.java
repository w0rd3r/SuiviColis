package com.example.ensai.suivicolis;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ensai on 10/05/16.
 */
public class BaseDonnees extends SQLiteOpenHelper {

    private static Integer DATABASE_VERSION=1;

    public BaseDonnees(Context context){
        super(context,"toto",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TRANS(nomTransporteur TEXT PRIMARY KEY, url TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Colis(numero TEXT PRIMARY KEY, nomTransporteur TEXT, description TEXT)");


        ContentValues values=new ContentValues();

        values.put("nomTransporteur", "Chronopost");
        values.put("url", "https://track.aftership.com/chronopost-france/%1");

        values.put("nomTransporteur", "Colissimo");
        values.put("url", "https://track.aftership.com/la-poste-colissimo/%1");

        values.put("nomTransporteur", "UPS");
        values.put("url", "https://track.aftership.com/ups/%");

        db.insert("TRANS", null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
