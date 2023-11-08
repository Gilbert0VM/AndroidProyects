package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNombre, etDia, etMes, etAno;
    Button btnEtapa;
    String nombre, dia, mes, ano;
    Intent i;
    Toast mensaje;
    Integer iDia,iMes,iAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDia = (EditText) findViewById(R.id.etDia);
        etMes = (EditText) findViewById(R.id.etMes);
        etAno = (EditText) findViewById(R.id.etAno);
        btnEtapa = (Button) findViewById(R.id.btnEtapa);



        btnEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnEtapa) {
                    if(etNombre.length() == 0 || etAno.length() == 0 || etMes.length() == 0 || etDia.length() == 0){
                        mensaje = Toast.makeText(MainActivity.this, "Rellene todos los campos por favor.",Toast.LENGTH_SHORT);
                        mensaje.show();
                    }else {

                        nombre = etNombre.getText().toString();
                        dia = etDia.getText().toString();
                        mes = etMes.getText().toString();
                        ano = etAno.getText().toString();

                        iDia = Integer.parseInt(dia);
                        iMes = Integer.parseInt(mes);
                        if(iDia>31 || iDia<1 || iMes>12 || iMes<1) {
                            mensaje = Toast.makeText(MainActivity.this,"Ingrese una fecha valida",Toast.LENGTH_SHORT);
                            mensaje.show();
                        }else {
                            i = new Intent(MainActivity.this,MainActivity2.class);
                            i.putExtra("Nombre", nombre);
                            i.putExtra("Dia", dia);
                            i.putExtra("Mes", mes);
                            i.putExtra("Ano", ano);
                            startActivity(i);
                            etDia.setText("");
                            etAno.setText("");
                            etMes.setText("");
                            etNombre.setText("");
                        }

                    }
                }
            }
        });
    }


}