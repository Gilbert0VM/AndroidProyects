package com.example.calculadora_simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnSumar, btnRestar, btnMulti, btnDividir, btnPotencia, btnReincio, btnCambio, btnCambio1, btncoheficiente, btnmodulo;
    EditText uno, dos;
    TextView result;

    String primero, segundo;
    Double n1, n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uno = (EditText) findViewById(R.id.number1);
        dos = (EditText) findViewById(R.id.number2);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnRestar = (Button) findViewById(R.id.btnRestar);
        btnDividir = (Button) findViewById(R.id.btnDividir);
        btnMulti = (Button) findViewById(R.id.btnMultiplicar);
        result = (TextView) findViewById(R.id.resultado);
        btnPotencia = (Button) findViewById(R.id.btnPotencia);
        btnReincio = (Button) findViewById(R.id.btnBorrar);
        btnCambio = (Button) findViewById(R.id.btnSigno);
        btnCambio1 = (Button) findViewById(R.id.btnSigno1);
        btnmodulo = (Button) findViewById(R.id.btnmodulo);
        btncoheficiente = (Button) findViewById(R.id.btncoheficiente);

        btnmodulo.setOnClickListener(this);
        btncoheficiente.setOnClickListener(this);
        btnCambio1.setOnClickListener(this);
        btnCambio.setOnClickListener(this);
        btnReincio.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDividir.setOnClickListener(this);
        btnSumar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnPotencia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        primero = uno.getText().toString(); //primero se convierte el texto (manera de llamar a lo visual de texto) en string para poder convertirlo en double en las siguientes lines.
        segundo = dos.getText().toString();

        if (primero.isEmpty() || segundo.isEmpty()) {
            primero = "0";
            segundo = "0";
        }
        n1 = Double.parseDouble(primero);
        n2 = Double.parseDouble(segundo); //para numeros doubles, se puede con los tipos de datos int, float, etc

        if (view.getId() == R.id.btnSumar) {
            result.setText(primero + " + " + segundo + " = " + (n1 + n2) + "");
        } else if (view.getId() == R.id.btnRestar) {
            result.setText(primero + " - " + segundo + " = " + (n1 - n2) + "");
        } else if (view.getId() == R.id.btnMultiplicar) {
            result.setText(primero + " * " + segundo + " = " + (n1 * n2) + "");
        } else if (view.getId() == R.id.btnDividir) {
            result.setText(primero + " / " + segundo + " = " + (n1 / n2) + "");
        } else if (view.getId() == R.id.btnPotencia) {
            result.setText(primero + " ^ " + segundo + " = " + Math.pow(n1, n2) + "");
        } else if (view.getId() == R.id.btnSigno) {
            uno.setText("" + -n1);
        } else if (view.getId() == R.id.btnSigno1) {
            dos.setText("" + -n2);
        } else if (view.getId() == R.id.btnmodulo) {
            result.
        } else if (view.getId() == R.id.btnBorrar) {
            result.setText("0");
            uno.setText("0");
            dos.setText("0");
        }

    }
}