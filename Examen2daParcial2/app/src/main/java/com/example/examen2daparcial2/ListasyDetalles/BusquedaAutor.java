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
import com.example.examen2daparcial2.databinding.ActivityBusquedaAutorBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroLibrosBinding;

import java.util.ArrayList;

public class BusquedaAutor extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ActivityBusquedaAutorBinding b;

    ArrayList<String> listaLibrosAutor;
    ArrayList<Libros> datosLibros;
    Intent i;
    String autor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityBusquedaAutorBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);
        autor = getIntent().getStringExtra("autor");
        mostrar();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaLibrosAutor);
        b.lvLista.setAdapter(adapter);
        b.lvLista.setOnItemClickListener(this);
    }
    public void mostrar() {
        Conectar conectar = new Conectar(this,Variables.NOMBRE_BD,null,1);
        String[] aut = {autor};
        SQLiteDatabase db = conectar.getReadableDatabase();
        Libros libro = null;
        datosLibros = new ArrayList<Libros>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Variables.NOMBRE_TABLA_LIBROS + " WHERE " + Variables.CAMPO_AUTOR + " = ?",aut);
        while(cursor.moveToNext()) {
            libro = new Libros();
            libro.setTitulo(cursor.getString(0));
            libro.setAutor(cursor.getString(1));
            libro.setEditorial(cursor.getString(2));
            libro.setPaginas(cursor.getInt(3));
            libro.setIsbn(cursor.getInt(4));
            datosLibros.add(libro);
        }
        agregarALista();
        db.close();
    }

    private void agregarALista() {
        listaLibrosAutor = new ArrayList<String>();
        for(int i=0; i<datosLibros.size(); i++) {
            listaLibrosAutor.add(datosLibros.get(i).getTitulo() + " - " + datosLibros.get(i).getAutor());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Libros usuario = datosLibros.get(position);
        i = new Intent(this, DetalleLibro.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("libros",usuario); //empaquetamos el objeto para que pueda ser enviado con la etiqueta usuario
        i.putExtras(bundle);
        startActivity(i);
    }
}