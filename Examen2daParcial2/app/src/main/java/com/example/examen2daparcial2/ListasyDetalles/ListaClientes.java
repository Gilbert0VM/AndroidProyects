package com.example.examen2daparcial2.ListasyDetalles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.examen2daparcial2.BaseDeDatos.Clientes;
import com.example.examen2daparcial2.BaseDeDatos.Conectar;
import com.example.examen2daparcial2.BaseDeDatos.Variables;
import com.example.examen2daparcial2.databinding.ActivityListaClientesBinding;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActivityListaClientesBinding b;

    ArrayList<String> listaClientes;
    ArrayList<Clientes> datosClientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaClientesBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        mostrar();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaClientes);
        b.lvLista.setAdapter(adapter);
        b.lvLista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Clientes cliente = datosClientes.get(position);
        Intent i = new Intent(this, DetalleCliente.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clientes",cliente);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void mostrar() {
        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        SQLiteDatabase db = conectar.getReadableDatabase();

        Clientes cliente = null;
        datosClientes = new ArrayList<Clientes>();
        Cursor cursor =db.rawQuery("SELECT * FROM " + Variables.NOMBRE_TABLA_CLIENTES, null);
        while(cursor.moveToNext()) { //mientras el cursor pueda moverse hacia adelante, o sea mientras tenga elementos por recorrer
            cliente = new Clientes();
            cliente.setId(cursor.getInt(0));
            cliente.setNombre_cliente(cursor.getString(1));
            cliente.setRfc(cursor.getString(2));
            datosClientes.add(cliente);
        }
        agregarLista();
        db.close();
    }
    public void agregarLista() {
        listaClientes = new ArrayList<String>();
        for(int i=0;i<datosClientes.size();i++) {
            listaClientes.add(datosClientes.get(i).getId() + " - " + datosClientes.get(i).getRfc());
        }
    }
}