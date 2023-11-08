package com.example.examen2parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.examen2parcial.databinding.ActivityDetalleBinding;
import com.example.examen2parcial.databinding.ActivityListaBinding;

public class detalle extends AppCompatActivity {

    private ActivityDetalleBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);
        Bundle objeto = getIntent().getExtras();
        Libros libro = null;
        if(objeto != null) {
            libro = (Libros) objeto.getSerializable("libros");
            b.tvTitulo.setText(libro.getTitulo().toString());
            b.tvAutor.setText(libro.getAutor().toString());
            b.tvEditorial.setText(libro.getEditorial().toString());
            b.tvPaginas.setText(libro.getPaginas().toString());
            b.tvISBN.setText(libro.getIsbn().toString());
        }
    }
}