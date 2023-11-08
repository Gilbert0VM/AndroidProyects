package com.example.examen2daparcial2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.examen2daparcial2.BaseDeDatos.Conectar;
import com.example.examen2daparcial2.BaseDeDatos.Variables;
import com.example.examen2daparcial2.ListasyDetalles.BusquedaAutor;
import com.example.examen2daparcial2.ListasyDetalles.BusquedaTitulo;
import com.example.examen2daparcial2.ListasyDetalles.ListaClientes;
import com.example.examen2daparcial2.ListasyDetalles.ListaLibros;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroLibrosBinding;

public class RegistroLibros extends AppCompatActivity {
    private ActivityRegistroLibrosBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRegistroLibrosBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        b.btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();

                try {
                    ContentValues valores = new ContentValues();
                    valores.put(Variables.CAMPO_TITULO,b.etTitulo.getText().toString());
                    valores.put(Variables.CAMPO_AUTOR,b.etAutor.getText().toString());
                    valores.put(Variables.CAMPO_EDITORIAL,b.etEditorial.getText().toString());
                    valores.put(Variables.CAMPO_PAGINAS,Integer.parseInt(b.etPaginas.getText().toString()));
                    valores.put(Variables.CAMPO_ISBN,Integer.parseInt(b.etISBN.getText().toString()));
                    valores.put(Variables.CAMPO_PRECIO,Double.parseDouble(b.etPrecio.getText().toString()));

                    long id = db.insert(Variables.NOMBRE_TABLA_LIBROS,Variables.CAMPO_ID_LIBROS,valores);
                    Toast.makeText(RegistroLibros.this, "Valores Ingresado con el ID: " + id, Toast.LENGTH_SHORT).show();
                    b.etAutor.setText("");
                    b.etEditorial.setText("");
                    b.etISBN.setText("");
                    b.etPaginas.setText("");
                    b.etTitulo.setText("");
                    b.etPrecio.setText("");
                    db.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroLibros.this, "Error al ingresar datos" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }); //Listo
        b.btnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etISBN.getText().toString()};
                Integer n = db.delete(Variables.NOMBRE_TABLA_LIBROS, Variables.CAMPO_ISBN + "=?",parametros);
                Toast.makeText(RegistroLibros.this,"Registros eliminados correctamente: " + n, Toast.LENGTH_SHORT).show();
                b.etAutor.setText("");
                b.etEditorial.setText("");
                b.etISBN.setText("");
                b.etPaginas.setText("");
                b.etTitulo.setText("");
                b.etPrecio.setText("");
                db.close();
            }
        }); //Listo
        b.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etISBN.getText().toString()};

                ContentValues valores = new ContentValues();

                try {
                    valores.put(Variables.CAMPO_TITULO,b.etTitulo.getText().toString());
                    valores.put(Variables.CAMPO_AUTOR,b.etAutor.getText().toString());
                    valores.put(Variables.CAMPO_EDITORIAL,b.etEditorial.getText().toString());
                    valores.put(Variables.CAMPO_PAGINAS,Integer.parseInt(b.etPaginas.getText().toString()));
                    valores.put(Variables.CAMPO_ISBN,Integer.parseInt(b.etISBN.getText().toString()));
                    valores.put(Variables.CAMPO_PRECIO,Double.parseDouble(b.etPrecio.getText().toString()));

                    long n = db.update(Variables.NOMBRE_TABLA_LIBROS, valores, Variables.CAMPO_ISBN + "=?", parametros);
                    Toast.makeText(RegistroLibros.this,"USUARIO "+ n +" ACTUALIZADO",Toast.LENGTH_SHORT).show();
                    b.etAutor.setText("");
                    b.etTitulo.setText("");
                    b.etEditorial.setText("");
                    b.etPaginas.setText("");
                    b.etISBN.setText("");
                    b.etPrecio.setText("");
                } catch (NumberFormatException e) {
                    Log.d(TAG, "fallo " + e);
                }
                db.close();
            }
        }); //Listo
        b.btnBusquedaAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etAutor.getText().toString()}; //busqueda por titulo
                String[] campos = {Variables.CAMPO_ID_LIBROS,Variables.CAMPO_TITULO,Variables.CAMPO_AUTOR,Variables.CAMPO_EDITORIAL,Variables.CAMPO_PAGINAS,Variables.CAMPO_ISBN,Variables.CAMPO_PRECIO};
                String[] columna = {"COUNT(*)"};
                try {
                    Cursor countCursor = db.query(Variables.NOMBRE_TABLA_LIBROS,columna,Variables.CAMPO_AUTOR + "= ?",parametros,null,null,null);
                    if(countCursor.moveToFirst()) {
                        int c = countCursor.getInt(0);
                        if(c>1) {
                            Toast.makeText(RegistroLibros.this,"N: " + c,Toast.LENGTH_SHORT).show();
                            i = new Intent(RegistroLibros.this, BusquedaAutor.class);
                            i.putExtra("autor", b.etAutor.getText().toString());
                            startActivity(i);
                        }
                        countCursor.close();
                    }
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_LIBROS,campos, Variables.CAMPO_AUTOR + "=?",parametros,null,null,Variables.CAMPO_AUTOR,"1");
                    cursor.moveToFirst();
                    b.etTitulo.setText(cursor.getString(1));
                    b.etAutor.setText(cursor.getString(2));
                    b.etEditorial.setText(cursor.getString(3));
                    b.etPaginas.setText(cursor.getString(4));
                    b.etISBN.setText(cursor.getString(5));
                    b.etPrecio.setText(cursor.getString(6));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroLibros.this, "USUARIO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etTitulo.setText("");
                    b.etAutor.setText("");
                    b.etEditorial.setText("");
                    b.etPaginas.setText("");
                    b.etISBN.setText("");
                    b.etPrecio.setText("");
                }
            }
        }); //Listo
        b.btnBuscarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etTitulo.getText().toString()}; //busqueda por titulo
                String[] campos = {Variables.CAMPO_ID_LIBROS,Variables.CAMPO_TITULO,Variables.CAMPO_AUTOR,Variables.CAMPO_EDITORIAL,Variables.CAMPO_PAGINAS,Variables.CAMPO_ISBN,Variables.CAMPO_PRECIO};
                String[] columna = {"COUNT(*)"};
                try {
                    Cursor countCursor = db.query(Variables.NOMBRE_TABLA_LIBROS,columna,Variables.CAMPO_TITULO + "= ?",parametros,null,null,null);
                    if(countCursor.moveToFirst()) {
                        int c = countCursor.getInt(0);
                        if(c>1) {
                            Toast.makeText(RegistroLibros.this,"N: " + c,Toast.LENGTH_SHORT).show();
                            i = new Intent(RegistroLibros.this, BusquedaTitulo.class);
                            i.putExtra("titulo", b.etTitulo.getText().toString());
                            startActivity(i);
                        }
                        countCursor.close();
                    }
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_LIBROS,campos, Variables.CAMPO_TITULO + "=?",parametros,null,null,Variables.CAMPO_TITULO,"1");
                    cursor.moveToFirst();
                    b.etTitulo.setText(cursor.getString(1));
                    b.etAutor.setText(cursor.getString(2));
                    b.etEditorial.setText(cursor.getString(3));
                    b.etPaginas.setText(cursor.getString(4));
                    b.etISBN.setText(cursor.getString(5));
                    b.etPrecio.setText(cursor.getString(6));
                    cursor.close();
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroLibros.this, "USUARIO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etTitulo.setText("");
                    b.etAutor.setText("");
                    b.etEditorial.setText("");
                    b.etPaginas.setText("");
                    b.etISBN.setText("");
                    b.etPrecio.setText("");
                }
            }
        });
        b.btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(RegistroLibros.this, ListaLibros.class);
                startActivity(i);
            }
        });
    }
}