package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;


public class MainActivity2 extends AppCompatActivity {

    TextView tvNombre, tvFecha, tvEdad, tvEtapas;
    String nombre, dia, mes, ano;
    Integer mes1,dia1, ano1, edad;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvFecha = (TextView) findViewById(R.id.tvDia);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvEtapas = (TextView) findViewById(R.id.tvEtapas);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        nombre = getIntent().getStringExtra("Nombre");
        dia = getIntent().getStringExtra("Dia");
        mes = getIntent().getStringExtra("Mes");
        ano = getIntent().getStringExtra("Ano");


        dia1 = Integer.parseInt(dia);
        mes1 = Integer.parseInt(mes);
        ano1 = Integer.parseInt(ano);
        edad = calcularCum(dia1,mes1,ano1);
        tvNombre.setText(nombre + "");
        tvFecha.setText(dia + " de " + obtenerNombreDelMes(mes1) + " del " + ano);
        tvEdad.setText(edad +" años");
        tvEtapas.setText(calcularEtapa(edad) + "");

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public String calcularEtapa(int edad) {
        String etapas;
        if(edad >= 0 && edad <= 2) {
            return etapas = "Maternal";
        } else if (edad >=3 && edad <= 5) {
            return etapas = "Kinder";
        } else if (edad >=6 && edad <= 12) {
            return etapas = "Primaria";
        } else if (edad >=13 && edad <= 15) {
            return etapas = "Secundaria";
        } else if (edad >=16 && edad <= 18) {
            return etapas = "Prepa";
        } else if (edad >=19 && edad <= 24) {
            return etapas = "Universidad";
        } else if (edad >=25 && edad <=65) {
            return etapas = "Trabajo";
        } else if (edad > 65) {
            return etapas = "Jubilado";
        }else {
            return etapas = "Edad Fuera de rango";
        }
    }
    public Integer calcularCum(int dia, int mes, int ano) {
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(ano, mes - 1, dia); // Meses en Calendar se cuentan desde 0 (enero) a 11 (diciembre)

        Calendar fechaActual = Calendar.getInstance();

        int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        // Comprobar si aún no ha pasado el cumpleaños este año
        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
    }

    public String obtenerNombreDelMes(int numeroMes) {
        String nombreMes;

        switch (numeroMes) {
            case 1:
                nombreMes = "Enero";
                break;
            case 2:
                nombreMes = "Febrero";
                break;
            case 3:
                nombreMes = "Marzo";
                break;
            case 4:
                nombreMes = "Abril";
                break;
            case 5:
                nombreMes = "Mayo";
                break;
            case 6:
                nombreMes = "Junio";
                break;
            case 7:
                nombreMes = "Julio";
                break;
            case 8:
                nombreMes = "Agosto";
                break;
            case 9:
                nombreMes = "Septiembre";
                break;
            case 10:
                nombreMes = "Octubre";
                break;
            case 11:
                nombreMes = "Noviembre";
                break;
            case 12:
                nombreMes = "Diciembre";
                break;
            default:
                nombreMes = "Mes no válido";
                break;
        }

        return nombreMes;
    }

}