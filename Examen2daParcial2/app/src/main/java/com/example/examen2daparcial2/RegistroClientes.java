package com.example.examen2daparcial2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.DefaultTaskExecutor;

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
import com.example.examen2daparcial2.ListasyDetalles.DetalleCliente;
import com.example.examen2daparcial2.ListasyDetalles.ListaClientes;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;

public class RegistroClientes extends AppCompatActivity {
    private ActivityRegistroClientesBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityRegistroClientesBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        b.btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();

                try {
                    ContentValues valores = new ContentValues();
                    valores.put(Variables.CAMPO_NOMBRE_CLIENTE, b.etNombreCliente.getText().toString());
                    valores.put(Variables.CAMPO_RFC,b.etRFC.getText().toString());

                    long id = db.insert(Variables.NOMBRE_TABLA_CLIENTES, Variables.CAMPO_ID_CLIENTES, valores);
                    Toast.makeText(RegistroClientes.this, "Valores ingresado con el ID: " + id, Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etNombreCliente.setText("");
                    b.etRFC.setText("");
                    db.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroClientes.this, "Error al ingresar datos" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }); //LISTO

        b.btnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etIDCliente.getText().toString()};
                Integer n = db.delete(Variables.NOMBRE_TABLA_CLIENTES, Variables.CAMPO_ID_CLIENTES + "=?",parametros);
                Toast.makeText(RegistroClientes.this,"Registros eliminados correctamente: " + n, Toast.LENGTH_SHORT).show();
                b.etIDCliente.setText("");
                b.etRFC.setText("");
                b.etNombreCliente.setText("");
                db.close();
            }
        }); //LISTO

        b.btnModificar.setOnClickListener(new View.OnClickListener() { //LISTO
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etIDCliente.getText().toString()};

                ContentValues valores = new ContentValues();

                try {
                    valores.put(Variables.CAMPO_ID_CLIENTES,b.etIDCliente.getText().toString());
                    valores.put(Variables.CAMPO_NOMBRE_CLIENTE,b.etNombreCliente.getText().toString());
                    valores.put(Variables.CAMPO_RFC,b.etRFC.getText().toString());

                    long n = db.update(Variables.NOMBRE_TABLA_CLIENTES, valores, Variables.CAMPO_ID_CLIENTES + "=?", parametros);
                    Toast.makeText(RegistroClientes.this,"USUARIO "+ n+" ACTUALIZADO",Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etNombreCliente.setText("");
                    b.etRFC.setText("");
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Error " + e);
                }
                db.close();
            }
        }); //LISTO

        b.btnBusquedaNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etNombreCliente.getText().toString()}; //busqueda por CLIENTE
                String[] campos = {Variables.CAMPO_ID_CLIENTES,Variables.CAMPO_NOMBRE_CLIENTE,Variables.CAMPO_RFC};
                String[] columna = {"COUNT(*)"};
                try {
                    Cursor countCursor = db.query(Variables.NOMBRE_TABLA_CLIENTES,columna,Variables.CAMPO_NOMBRE_CLIENTE + "= ?",parametros,null,null,null);
                    if(countCursor.moveToFirst()) {
                        int c = countCursor.getInt(0);
                        if(c>1) {
                            Toast.makeText(RegistroClientes.this,"N: " + c,Toast.LENGTH_SHORT).show();
                            i = new Intent(RegistroClientes.this, DetalleCliente.class);
                            i.putExtra("cliente", b.etNombreCliente.getText().toString());
                            startActivity(i);
                        }
                        countCursor.close();
                    }
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_CLIENTES,campos,Variables.CAMPO_NOMBRE_CLIENTE + "= ?",parametros,null,null,null);
                    cursor.moveToFirst();
                    b.etIDCliente.setText(cursor.getString(0));
                    b.etNombreCliente.setText(cursor.getString(1));
                    b.etRFC.setText(cursor.getString(2));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroClientes.this, "USUARIO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etRFC.setText("");
                    b.etNombreCliente.setText("");
                }
            }
        });
        b.btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(RegistroClientes.this, ListaClientes.class);
                startActivity(i);
            }
        });
    }
}