package com.example.maratonboston;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class boston extends AppCompatActivity implements View.OnClickListener {
    String nombre, edad, sexo;
    TextView tvNombre, tvEdad, tvSexo, tvResultado;
    Button btnCalcular, btnSalir;
    Intent i;
    Double dEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boston);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvSexo = (TextView) findViewById(R.id.tvSexo);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        nombre = getIntent().getStringExtra("Nombre");
        edad = getIntent().getStringExtra("Edad");
        sexo = getIntent().getStringExtra("Sexo");
        tvNombre.setText(nombre);
        tvEdad.setText(edad + " Años");
        tvSexo.setText(sexo);
        dEdad = Double.parseDouble(edad);
        btnCalcular.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalir) {
            i = new Intent(boston.this, MainActivity.class);
            startActivity(i);
        }else if (v.getId() == R.id.btnCalcular){
            if(sexo.equals("Mujer")) {
                if(dEdad >= 18 && dEdad <= 34) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:30h");
                } else if (dEdad >= 35 && dEdad <= 39) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:35h");
                } else if (dEdad >= 40 && dEdad <= 44) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:40h");
                } else if (dEdad >= 45 && dEdad <=49) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:50h");
                } else if (dEdad >= 50 && dEdad <=54) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:55h");
                } else if (dEdad >= 55 && dEdad <=59) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:05h");
                } else if (dEdad >= 60 && dEdad <=64) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:20h");
                } else if (dEdad >= 65 && dEdad <=69) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:35h");
                } else if (dEdad >= 70 && dEdad <=74) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:50h");
                } else if (dEdad >= 75 && dEdad <=79) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 5:05h");
                }else if(dEdad >=80) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 5:20h");
                }
            }else if(sexo.equals("Hombre")) {
                if(dEdad >= 18 && dEdad <= 34) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:00h");
                } else if (dEdad >= 35 && dEdad <= 39) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:05h");
                } else if (dEdad >= 40 && dEdad <= 44) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:10h");
                } else if (dEdad >= 45 && dEdad <=49) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:20h");
                } else if (dEdad >= 50 && dEdad <=54) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:25h");
                } else if (dEdad >= 55 && dEdad <=59) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:35h");
                } else if (dEdad >= 60 && dEdad <=64) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 3:50h");
                } else if (dEdad >= 65 && dEdad <=69) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:05h");
                } else if (dEdad >= 70 && dEdad <=74) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:20h");
                } else if (dEdad >= 75 && dEdad <=79) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:35h");
                }else if(dEdad >=80) {
                    tvResultado.setText(nombre + " usted requiere un tiempo de 4:50h");
                }
            }
        }
    }
}