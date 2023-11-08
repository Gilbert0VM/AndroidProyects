package com.example.examen2daparcial2.ListasyDetalles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.examen2daparcial2.BaseDeDatos.Clientes;
import com.example.examen2daparcial2.BaseDeDatos.Libros;
import com.example.examen2daparcial2.R;
import com.example.examen2daparcial2.databinding.ActivityDetalleClienteBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;

public class DetalleCliente extends AppCompatActivity {
    private ActivityDetalleClienteBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDetalleClienteBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        Bundle objeto = getIntent().getExtras();
        Clientes cliente = null;
        if(objeto != null) {
            cliente = (Clientes) objeto.getSerializable("clientes");
            b.tvID.setText(cliente.getId().toString());
            b.tvNombreCliente.setText(cliente.getNombre_cliente().toString());
            b.tvRFC.setText(cliente.getRfc().toString());
        }

    }
}