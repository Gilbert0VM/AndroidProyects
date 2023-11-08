package com.example.examen2daparcial2.ListasyDetalles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.examen2daparcial2.BaseDeDatos.Clientes;
import com.example.examen2daparcial2.BaseDeDatos.Ventas;
import com.example.examen2daparcial2.R;
import com.example.examen2daparcial2.databinding.ActivityDetalleVentaBinding;
import com.example.examen2daparcial2.databinding.ActivityListaVentasBinding;

public class DetalleVenta extends AppCompatActivity {
    private ActivityDetalleVentaBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDetalleVentaBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        Bundle objeto = getIntent().getExtras();
        Ventas ventas = null;
        if(objeto != null) {
            ventas = (Ventas) objeto.getSerializable("ventas");
            b.tvIDVenta.setText(ventas.getId_venta().toString());
            b.tvIDCliente.setText(ventas.getId_cliente().toString());
            b.tvIDLibro.setText(ventas.getId_libro().toString());
            b.tvCantidad.setText(ventas.getCantidad().toString());
            b.tvCostoTotal.setText("$" + ventas.getCostoTotal().toString());
        }
    }
}