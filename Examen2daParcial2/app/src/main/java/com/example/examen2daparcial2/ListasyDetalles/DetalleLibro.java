package com.example.examen2daparcial2.ListasyDetalles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.examen2daparcial2.BaseDeDatos.Libros;
import com.example.examen2daparcial2.R;
import com.example.examen2daparcial2.databinding.ActivityDetalleLibroBinding;

public class DetalleLibro extends AppCompatActivity {
    private ActivityDetalleLibroBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleLibroBinding.inflate(getLayoutInflater());
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
            b.tvPrecio.setText(libro.getPrecio().toString());
        }

    }
}