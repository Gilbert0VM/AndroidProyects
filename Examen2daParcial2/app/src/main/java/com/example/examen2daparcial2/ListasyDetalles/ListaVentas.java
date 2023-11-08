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
import com.example.examen2daparcial2.BaseDeDatos.Ventas;
import com.example.examen2daparcial2.R;
import com.example.examen2daparcial2.databinding.ActivityListaVentasBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;

import java.util.ArrayList;

public class ListaVentas extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityListaVentasBinding b;
    ArrayList<String> listaVentas;
    ArrayList<Ventas> datosVentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaVentasBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);
        mostrar();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaVentas);
        b.lvLista.setAdapter(adapter);
        b.lvLista.setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ventas venta = datosVentas.get(position);
        Intent i = new Intent(this,DetalleVenta.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ventas",venta);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void mostrar() {
        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        SQLiteDatabase db = conectar.getReadableDatabase();

        Ventas venta = null;
        datosVentas = new ArrayList<Ventas>();
        Cursor cursor =db.rawQuery("SELECT * FROM " + Variables.NOMBRE_TABLA_VENTAS, null);
        while(cursor.moveToNext()) { //mientras el cursor pueda moverse hacia adelante, o sea mientras tenga elementos por recorrer
            venta = new Ventas();
            venta.setId_venta(cursor.getInt(0));
            venta.setId_cliente(cursor.getString(1));
            venta.setId_libro(cursor.getInt(2));
            venta.setCantidad(cursor.getInt(3));
            venta.setCostoTotal(cursor.getDouble(4));
            datosVentas.add(venta);
        }
        agregarLista();
        db.close();
    }
    public void agregarLista() {
        listaVentas = new ArrayList<String>();
        for(int i=0;i<datosVentas.size();i++) {
            listaVentas.add(datosVentas.get(i).getId_venta() + " - " + datosVentas.get(i).getId_cliente());
        }
    }
}