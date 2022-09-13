package com.app.arrivalsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    DatabaseHelper db1;
    // final String CREAR_TABLA_USUARIO = "CREATE TABLE usuarios (id INTEGER, nombre TEXT, telefono TEXT)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db1.execSQL(Utilidades.CREAR_TABLA_USUARIO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db1, int versionAntigua, int versionNueva) {
        db1.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db1);
    }
}
