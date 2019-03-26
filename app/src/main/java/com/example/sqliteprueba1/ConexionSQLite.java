package com.example.sqliteprueba1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteprueba1.Utilidades.Constantes;

public class ConexionSQLite extends SQLiteOpenHelper {


    public ConexionSQLite( Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.CREATE_USER_TABLE);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
