package com.example.loginbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class inicio extends AppCompatActivity {

    TextView txt;
    String name, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txt = (TextView) findViewById(R.id.txtbienvenida);
        name = getIntent().getStringExtra("usuario");
        password = getIntent().getStringExtra("pass");
        txt.setText("Bienvenido " + name + " tu contraseña es "+ password);
    }
}