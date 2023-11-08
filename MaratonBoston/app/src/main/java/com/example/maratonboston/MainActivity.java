package com.example.maratonboston;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etEdad;
    RadioButton rbHombre, rbMujer;
    RadioGroup radioGroup;
    Button btnCalorias, btnBoston;
    Intent i;
    String nombre, edad, sexo;
    Double dEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText) findViewById(R.id.etEdad);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnBoston = (Button) findViewById(R.id.btnBoston);
        rbHombre = (RadioButton)findViewById(R.id.rbHombre);
        rbMujer = (RadioButton) findViewById(R.id.rbMujer);
        radioGroup = findViewById(R.id.radiogroup);
        btnCalorias.setOnClickListener(this);
        btnBoston.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        nombre = etNombre.getText().toString();
        edad = etEdad.getText().toString();
        if(v.getId() == R.id.btnCalorias){
            if(nombre.length() != 0 && edad.length() != 0 && (rbHombre.isChecked() || rbMujer.isChecked())) {
                if(rbMujer.isChecked()) {
                    sexo = "Mujer";
                }else if(rbHombre.isChecked()) {
                    sexo = "Hombre";
                }
                i = new Intent(MainActivity.this, Calorias.class);
                i.putExtra("Nombre",nombre);
                i.putExtra("Edad",edad);
                i.putExtra("Sexo",sexo);
                startActivity(i);
            }else {
                Toast.makeText(this,"Rellena la información",Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.btnBoston) {
            if(nombre.length() != 0 && edad.length() != 0 && (rbHombre.isChecked() || rbMujer.isChecked())) {
                dEdad = Double.parseDouble(edad);
                if(rbMujer.isChecked()) {
                    sexo = "Mujer";
                }else if(rbHombre.isChecked()) {
                    sexo = "Hombre";
                }
                if(dEdad>17) {
                    i = new Intent(MainActivity.this, boston.class);
                    i.putExtra("Nombre", nombre);
                    i.putExtra("Edad", edad);
                    i.putExtra("Sexo", sexo);
                    startActivity(i);
                }else {
                    Toast.makeText(this,"Edad no permitida para clasificar al Maratón",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this,"Rellena la información",Toast.LENGTH_SHORT).show();
            }
        }
    }
}