package com.example.spinner1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Spinner animales1, animales2;

    String[] opciones = {"Oso","Tigre","Elefante", "Perro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animales2 = (Spinner) findViewById(R.id.animales2);
        animales1 = (Spinner) findViewById(R.id.animales1);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,opciones);

        animales1.setAdapter(aa);
        animales1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.animales1) {

            Toast.makeText(this, i + "" + opciones[i],Toast.LENGTH_SHORT).show();
        }
        if(adapterView.getId() == R.id.animales2) {

            Toast.makeText(this, i + "" + opciones[i],Toast.LENGTH_SHORT).show();
        }
    }
}