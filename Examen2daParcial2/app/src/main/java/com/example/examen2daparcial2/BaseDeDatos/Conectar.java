package com.example.examen2daparcial2.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conectar extends SQLiteOpenHelper {

    public Conectar(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Variables.CREAR_TABLA_CLIENTES);
        db.execSQL(Variables.CREAR_TABLA_LIBROS);
        db.execSQL(Variables.CREAR_TABLA_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Variables.ELIMINAR_TABLA_CLIENTES);
        db.execSQL(Variables.ELIMINAR_TABLA_LIBROS);
        db.execSQL(Variables.ELIMINAR_TABLA_VENTAS);
        onCreate(db);
    }
}
