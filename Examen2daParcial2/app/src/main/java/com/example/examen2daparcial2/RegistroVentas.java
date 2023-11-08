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
import com.example.examen2daparcial2.ListasyDetalles.DetalleVenta;
import com.example.examen2daparcial2.ListasyDetalles.ListaLibros;
import com.example.examen2daparcial2.ListasyDetalles.ListaVentas;
import com.example.examen2daparcial2.databinding.ActivityRegistroClientesBinding;
import com.example.examen2daparcial2.databinding.ActivityRegistroVentasBinding;

public class RegistroVentas extends AppCompatActivity {
    private ActivityRegistroVentasBinding b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRegistroVentasBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        b.btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();

                try {
                    ContentValues valores = new ContentValues();
                    //valores.put(Variables.CAMPO_ID_VENTA,Integer.parseInt(b.etIDVenta.getText().toString()));
                    valores.put(Variables.CAMPO_ID_CLIENTES,b.etIDCliente.getText().toString());
                    valores.put(Variables.CAMPO_ID_LIBROS,Integer.parseInt(b.etIDLibro.getText().toString()));
                    valores.put(Variables.CAMPO_CANTIDAD_LIBROS,Integer.parseInt(b.etCantidadLibros.getText().toString()));
                    valores.put(Variables.CAMPO_COSTO_TOTAL,Double.parseDouble(b.etCostoTotal.getText().toString()));

                    long id = db.insert(Variables.NOMBRE_TABLA_VENTAS, Variables.CAMPO_ID_VENTA, valores);
                    Toast.makeText(RegistroVentas.this, "Valores ingresado con el ID: " + id, Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etIDLibro.setText("");
                    b.etIDVenta.setText("");
                    b.etCostoTotal.setText("");
                    b.etCantidadLibros.setText("");
                    db.close();
                } catch (Exception e) {
                    Log.e(TAG,"NO MAMES " + e + "LINEA: "+ e.getStackTrace()[0].getLineNumber());
                }
            }
        }); //LISTO

        b.btnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etIDVenta.getText().toString()};
                Integer n = db.delete(Variables.NOMBRE_TABLA_VENTAS, Variables.CAMPO_ISBN + "=?",parametros);
                Toast.makeText(RegistroVentas.this,"Registros eliminados correctamente: " + n, Toast.LENGTH_SHORT).show();
                b.etIDCliente.setText("");
                b.etIDLibro.setText("");
                b.etIDVenta.setText("");
                b.etCostoTotal.setText("");
                b.etCantidadLibros.setText("");
                db.close();
            }
        }); //Listo

        b.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametros = {b.etIDVenta.getText().toString()};


                try {
                    ContentValues valores = new ContentValues();

                    valores.put(Variables.CAMPO_ID_CLIENTES,b.etIDCliente.getText().toString());
                    valores.put(Variables.CAMPO_ID_LIBROS,Integer.parseInt(b.etIDLibro.getText().toString()));
                    valores.put(Variables.CAMPO_CANTIDAD_LIBROS,Integer.parseInt(b.etCantidadLibros.getText().toString()));
                    valores.put(Variables.CAMPO_COSTO_TOTAL,Double.parseDouble(b.etCostoTotal.getText().toString()));

                    long n = db.update(Variables.NOMBRE_TABLA_VENTAS, valores, Variables.CAMPO_ID_VENTA + "=?", parametros);
                    Toast.makeText(RegistroVentas.this,"USUARIO "+ n +" ACTUALIZADO",Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etIDLibro.setText("");
                    b.etIDVenta.setText("");
                    b.etCostoTotal.setText("");
                    b.etCantidadLibros.setText("");

                } catch (NumberFormatException e) {
                    Toast.makeText(RegistroVentas.this,"ERROR AL ACTUALIZAR LOS DATOS",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        }); //Listo

        b.btnBuscarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etIDLibro.getText().toString()}; //busqueda por id del libro
                String[] campos = {Variables.CAMPO_TITULO,Variables.CAMPO_PRECIO};
                try {
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_LIBROS,campos,Variables.CAMPO_ISBN + "= ?",parametros,null,null,null);
                    cursor.moveToFirst();
                    b.tvDatosLibro.setText("Titulo: " + cursor.getString(0) +", \nPrecio: $" + cursor.getString(1));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroVentas.this, "LIBRO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etIDLibro.setText("");
                    b.etIDVenta.setText("");
                    b.etCostoTotal.setText("");
                    b.etCantidadLibros.setText("");
                }
            }
        }); //Listo

        b.btnBuscarRFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etIDCliente.getText().toString()}; //busqueda por rfc del libro
                String[] campos = {Variables.CAMPO_NOMBRE_CLIENTE,Variables.CAMPO_RFC};
                try {
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_CLIENTES,campos,Variables.CAMPO_RFC + "= ?",parametros,null,null,null);
                    cursor.moveToFirst();
                    b.tvDatosCliente.setText("Nombre: " + cursor.getString(0) +", \nRFC: " + cursor.getString(1));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroVentas.this, "USUARIO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etIDLibro.setText("");
                    b.etIDVenta.setText("");
                    b.etCostoTotal.setText("");
                    b.etCantidadLibros.setText("");
                }
            }
        }); //Listo

        b.btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(RegistroVentas.this, ListaVentas.class);
                startActivity(i);
            }
        }); //Listo

        b.btnBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conectar.getReadableDatabase();
                String[] parametros = {b.etIDCliente.getText().toString()}; //busqueda por titulo
                String[] campos = {Variables.CAMPO_ID_VENTA,Variables.CAMPO_ID_CLIENTES,Variables.CAMPO_ID_LIBROS,Variables.CAMPO_CANTIDAD_LIBROS,Variables.CAMPO_COSTO_TOTAL};
                String[] columna = {"COUNT(*)"};
                try {
                    Cursor countCursor = db.query(Variables.NOMBRE_TABLA_VENTAS,columna,Variables.CAMPO_ID_CLIENTES + "= ?",parametros,null,null,null);
                    if(countCursor.moveToFirst()) {
                        int c = countCursor.getInt(0);
                        if(c>1) {
                            Toast.makeText(RegistroVentas.this,"N: " + c,Toast.LENGTH_SHORT).show();
                            i = new Intent(RegistroVentas.this, ListaVentas.class);
                            i.putExtra("ventas", b.etIDCliente.getText().toString());
                            startActivity(i);
                        }
                        countCursor.close();
                    }
                    Cursor cursor = db.query(Variables.NOMBRE_TABLA_VENTAS,campos, Variables.CAMPO_ID_CLIENTES + "=?",parametros,null,null,null,null);
                    cursor.moveToFirst();
                    b.etIDVenta.setText(cursor.getString(0));
                    b.etIDCliente.setText(cursor.getString(1));
                    b.etIDLibro.setText(cursor.getString(2));
                    b.etCantidadLibros.setText(cursor.getString(3));
                    b.etCostoTotal.setText(cursor.getString(4));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(RegistroVentas.this, "USUARIO NO EXISTENTE", Toast.LENGTH_SHORT).show();
                    b.etIDCliente.setText("");
                    b.etIDLibro.setText("");
                    b.etIDVenta.setText("");
                    b.etCostoTotal.setText("");
                    b.etCantidadLibros.setText("");
                }
            }
        });//Listo
    }
}