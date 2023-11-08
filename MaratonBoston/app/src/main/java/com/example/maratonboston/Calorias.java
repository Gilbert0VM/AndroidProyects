package com.example.maratonboston;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calorias extends AppCompatActivity  implements View.OnClickListener{

    String nombre, edad, sexo, peso,estatura;
    TextView tvNombre,tvEdad, tvSexo, tvresultado;
    EditText etPeso, etEstatura;
    Button btnCalcular, btnSalir;
    Double dPeso, dEstatura, dEdad, resultado;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorias);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvSexo = (TextView) findViewById(R.id.tvSexo);
        etPeso = (EditText) findViewById(R.id.etPeso);
        tvresultado = (TextView) findViewById(R.id.tvResultado);
        etEstatura = (EditText) findViewById(R.id.etEstatura);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);
        nombre = getIntent().getStringExtra("Nombre");
        edad = getIntent().getStringExtra("Edad");
        sexo = getIntent().getStringExtra("Sexo");
        tvNombre.setText(nombre);
        tvEdad.setText(edad + " Años");
        tvSexo.setText(sexo);

    }
   @Override
   public void onClick(View v) {
        peso = etPeso.getText().toString();
        estatura = etEstatura.getText().toString();
        if(v.getId() == R.id.btnSalir) {
            i = new Intent(Calorias.this, MainActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.btnCalcular) {
            if (peso.length() != 0 && estatura.length() != 0) {
                dPeso = Double.parseDouble(peso);
                dEstatura = Double.parseDouble(estatura);
                dEdad = Double.parseDouble(edad);
                if(sexo.equals("Mujer")) {
                    resultado = (10 * dPeso) + (6.25 * dEstatura) - (5 * dEdad) - 161;
                    resultado.toString();
                    tvresultado.setText(resultado + " kcal/dia");
                } else if (sexo.equals("Hombre")) {
                    resultado = (10 * dPeso) + (6.25 * dEstatura) - (5 * dEdad) + 5;
                    resultado.toString();
                    tvresultado.setText(resultado + " kcal/dia");
                }
            }else {
                Toast.makeText(this,nombre + " Rellena los campos", Toast.LENGTH_SHORT).show();
            }
        }
   }
}