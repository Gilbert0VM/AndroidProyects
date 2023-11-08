package com.example.calculadoracientifica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button sumar, restar, multiplicar, dividir, traiz,
            t10sobrex, tpotencia,tcambio, tcuadrado, tabsoluto, tcubo, tseno, tcoseno, ttangente,
            tfactorial,tlog,tlog10,tdivision1, tmodulo, tdivisionentera;
    Button t0,t1,t2,t3,t4,t5,t6,t7,t8,t9;
    Button tpunto,tigual,tborrar,tborrartodo, tpi;
    EditText num;
    Double op1,op2;
    TextView resultado;
    int operador, op3;
    String primero, segundo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tpi = (Button) findViewById(R.id.tpi);
        num = (EditText) findViewById(R.id.numero);
        resultado = (TextView) findViewById(R.id.operacion);
        sumar = (Button) findViewById(R.id.tsumar);
        restar = (Button) findViewById(R.id.trestar);
        multiplicar = (Button) findViewById(R.id.tmultiplclar);
        dividir = (Button) findViewById(R.id.tdividir);
        tpunto = (Button) findViewById(R.id.tpunto);
        tigual = (Button) findViewById(R.id.tigual);
        tborrar = (Button) findViewById(R.id.tborrar);
        tborrartodo = (Button) findViewById(R.id.tborrartodo);
        traiz = (Button) findViewById(R.id.traiz);
        tpotencia = (Button) findViewById(R.id.tpotencia);
        tcambio = (Button) findViewById(R.id.tcambio);
        tcuadrado = (Button) findViewById(R.id.tcuadrado);
        tabsoluto = (Button) findViewById(R.id.tabsoluto);
        tcubo = (Button) findViewById(R.id.tcubo);
        tseno = (Button) findViewById(R.id.tseno);
        tcoseno = (Button) findViewById(R.id.tcoseno);
        ttangente = (Button) findViewById(R.id.ttangente);
        tfactorial = (Button) findViewById(R.id.tfactorial);
        tlog = (Button) findViewById(R.id.tlog);
        tlog10 = (Button) findViewById(R.id.tlog10);
        tdivision1 = (Button) findViewById(R.id.tdivision1);
        tmodulo = (Button) findViewById(R.id.tmodulo);
        tdivisionentera = (Button) findViewById(R.id.tdivisionentera);
        t10sobrex = (Button) findViewById(R.id.t10sobrex);
        t10sobrex.setOnClickListener(this);
        tmodulo.setOnClickListener(this);
        tdivisionentera.setOnClickListener(this);
        tdivision1.setOnClickListener(this);
        tlog10.setOnClickListener(this);
        tlog.setOnClickListener(this);
        tpunto.setOnClickListener(this);
        tfactorial.setOnClickListener(this);
        ttangente.setOnClickListener(this);
        tcoseno.setOnClickListener(this);
        tseno.setOnClickListener(this);
        tcubo.setOnClickListener(this);
        tabsoluto.setOnClickListener(this);
        tcuadrado.setOnClickListener(this);
        tcambio.setOnClickListener(this);
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        tigual.setOnClickListener(this);
        tborrar.setOnClickListener(this);
        tborrartodo.setOnClickListener(this);
        tpotencia.setOnClickListener(this);
        traiz.setOnClickListener(this);
        tpi.setOnClickListener(this);

        t0 = (Button) findViewById(R.id.t0);
        t1 = (Button) findViewById(R.id.t1);
        t2 = (Button) findViewById(R.id.t2);
        t3 = (Button) findViewById(R.id.t3);
        t4 = (Button) findViewById(R.id.t4);
        t5 = (Button) findViewById(R.id.t5);
        t6 = (Button) findViewById(R.id.t6);
        t7 = (Button) findViewById(R.id.t7);
        t8 = (Button) findViewById(R.id.t8);
        t9 = (Button) findViewById(R.id.t9);
        t0.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);
        t8.setOnClickListener(this);
        t9.setOnClickListener(this);

     }

    public void onClick(View v) {

        if(v.getId() == R.id.t0){
            num.setText(num.getText() + "0");
        }else if ( v.getId() == R.id.tpunto) {
            num.setText(num.getText()+".");
        }else if( v.getId() == R.id.t1) {
            num.setText(num.getText() + "1");
        }else if( v.getId() == R.id.t2) {
            num.setText(num.getText() + "2");
        }else if( v.getId() == R.id.t3) {
            num.setText(num.getText() + "3");
        }else if( v.getId() == R.id.t4) {
            num.setText(num.getText() + "4");
        }else if( v.getId() == R.id.t5) {
            num.setText(num.getText() + "5");
        }else if( v.getId() == R.id.t6) {
            num.setText(num.getText() + "6");
        }else if( v.getId() == R.id.t7) {
            num.setText(num.getText() + "7");
        }else if( v.getId() == R.id.t8) {
            num.setText(num.getText() + "8");
        }else if( v.getId() == R.id.t9) {
            num.setText(num.getText() + "9");
        }else if( v.getId() == R.id.tborrar) {
            num.setText("");
        }else if( v.getId() == R.id.tborrartodo) {
            resultado.setText("");
            num.setText("");
        }else if( v.getId() == R.id.tcambio) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            num.setText(op1*(-1) + "");
        } else if ( v.getId() ==  R.id.tcuadrado) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(Math.pow(op1,2) + "");
        }else if ( v.getId() == R.id.tabsoluto) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.abs(op1)) + "");
        }else if ( v.getId() == R.id.tcubo) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(Math.pow(op1,3) + "");
        } else if ( v.getId() == R.id.tseno) { //pi radianes
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.sin(op1)) + "");
        } else if ( v.getId() == R.id.tcoseno) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.cos(op1)) + "");
        } else if ( v.getId() == R.id.ttangente) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.tan(op1)) + "");
        }else if( v.getId() == R.id.t10sobrex) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.pow(10,op1)) + "");
        }else if ( v.getId() == R.id.tfactorial) {
            primero = num.getText().toString();
            op3 = Integer.parseInt(primero);
            if(op3>=0 && op3 == (int) op3){
                resultado.setText((calcularFactorial(op3)) + "");
            }
        }else if( v.getId() == R.id.tpi) {
            num.setText(Math.PI + "");
        }else if ( v.getId() == R.id.tdivision1) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((1/op1) + "");

        } else if( v.getId() == R.id.tlog) { //logarimo nat en rad "in"
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.log(op1)) + "");
        }else if( v.getId() == R.id.tlog10) { //log base 10 "log"
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText((Math.log10(op1)) + "");
        }else if( v.getId() == R.id.traiz) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(Math.sqrt(op1) + "");
            num.setText("");
        } else if( v.getId() == R.id.tsumar) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "+");
            num.setText("");
            operador = 0;
        }else if( v.getId() == R.id.trestar) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "-");
            num.setText("");
            operador = 1;
        }else if( v.getId() == R.id.tmultiplclar) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "*");
            num.setText("");
            operador = 2;
        }else if( v.getId() == R.id.tdividir) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "/");
            num.setText("");
            operador = 3;
        }else if( v.getId() == R.id.tpotencia) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "^");
            num.setText("");
            operador = 4;
        } else if (v.getId() == R.id.tmodulo) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "%");
            num.setText("");
            operador = 5;
        } else if (v.getId() == R.id.tdivisionentera) {
            primero = num.getText().toString();
            op1 = Double.parseDouble(primero);
            resultado.setText(num.getText() + "/");
            num.setText("");
            operador = 6;
        }else if( v.getId() == R.id.tigual) {
            segundo = num.getText().toString();
            op2 = Double.parseDouble(segundo);
            if(operador == 0){
                resultado.setText((op1+op2) + "");
            }else if(operador == 1) {
                resultado.setText((op1-op2) + "");
            }else if(operador == 2) {
                resultado.setText((op1*op2) + "");
            }else if(operador == 3) {
                resultado.setText((op1/op2) + "");
            }else if(operador == 4) {
                resultado.setText((Math.pow(op1,op2)) + "");
            }else if(operador == 5) {
                resultado.setText((op1 % op2) + "");
            }else if(operador == 6) {
                resultado.setText((int)(op1/op2) + "");
            }
        }
    }
    public static long calcularFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calcularFactorial(n - 1);
        }
    }
}