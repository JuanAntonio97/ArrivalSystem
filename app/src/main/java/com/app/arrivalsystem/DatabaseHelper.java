package com.app.arrivalsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);

    }

    @Override//crea tabla de usuarios
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
    }

    //insertando en base de datos
    public boolean insert(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email );
        contentValues.put("password", pass);
        long ins = db.insert("user",null,contentValues);
        if (ins==-1)return false;
        else return true;
    }
    //checando si el mail o usuario ya existe
    public Boolean chkmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from e1 where e2=?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;

    }

    public void execSQL(String crearTablaUsuario) {
    }
}
