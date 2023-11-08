package com.example.examen2daparcial2.ListasyDetalles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.examen2daparcial2.BaseDeDatos.Conectar;
import com.example.examen2daparcial2.BaseDeDatos.Libros;
import com.example.examen2daparcial2.BaseDeDatos.Variables;
import com.example.examen2daparcial2.R;
import com.example.examen2daparcial2.databinding.ActivityListaLibrosBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;

import java.util.ArrayList;

public class ListaLibros extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityListaLibrosBinding b;

    ArrayList<String> listaLibros;
    ArrayList<Libros> datosLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaLibrosBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        mostrar();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaLibros);
        b.lvLista.setAdapter(adapter);
        b.lvLista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Libros libro = datosLibros.get(position);
        Intent i = new Intent(this,DetalleLibro.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("libros",libro);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void mostrar() {
        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        SQLiteDatabase db = conectar.getReadableDatabase();

        Libros libro = null;
        datosLibros = new ArrayList<Libros>();
        Cursor cursor =db.rawQuery("SELECT * FROM " + Variables.NOMBRE_TABLA_LIBROS, null);
        while(cursor.moveToNext()) { //mientras el cursor pueda moverse hacia adelante, o sea mientras tenga elementos por recorrer
            libro = new Libros();
            libro.setId(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setEditorial(cursor.getString(3));
            libro.setPaginas(cursor.getInt(4));
            libro.setIsbn(cursor.getInt(5));
            libro.setPrecio(cursor.getDouble(6));
            datosLibros.add(libro);
        }
        agregarLista();
        db.close();
    }
    public void agregarLista() {
        listaLibros = new ArrayList<String>();
        for(int i=0;i<datosLibros.size();i++) {
            listaLibros.add(datosLibros.get(i).getTitulo() + " - " + datosLibros.get(i).getAutor());
        }
    }
}