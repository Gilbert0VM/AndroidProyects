package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen.Model.EarthquakeFeature;
import com.example.examen.Model.EarthquakeResponse;
import com.example.examen.Interface.EarthquakeService;
import com.example.examen.Model.Properties;
import com.example.examen.adapter.alertAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String cantidades[] = {"Seleccione una opcion","Mostrar Ultimo", "Mostrar ultimos 3", "Mostrar ultimos 5", "Mostrar todos"};
    Spinner sCantidad;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvAlertas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sCantidad = (Spinner) findViewById(R.id.sCantidad);

        ArrayAdapter<String> aa = new ArrayAdapter<String>( this, R.layout.item_spinner,cantidades);

        sCantidad.setAdapter(aa);
        sCantidad.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.sCantidad) {
            if(position == 1) {
                getPost(1);
            } else if (position == 2) {
                getPost(3);
            } else if (position == 3) {
                getPost(5);
            } else if (position == 4) {
                getPost(0);

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getPost(int c) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EarthquakeService service = retrofit.create(EarthquakeService.class);

        Call<EarthquakeResponse> call = service.getEarthquakeData();
        call.enqueue(new Callback<EarthquakeResponse>() {
            @Override
            public void onResponse(Call<EarthquakeResponse> call, Response<EarthquakeResponse> response) {
                if (response.isSuccessful()) {
                    EarthquakeResponse earthquakeResponse = response.body();
                    List<EarthquakeFeature> earthquakes = earthquakeResponse.getFeatures();

                    // Actualiza el RecyclerView con los datos de terremotos
                    alertAdapter adapter = new alertAdapter(earthquakes, c);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Manejar errores de respuesta
                    //txt.setText("Código de respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<EarthquakeResponse> call, Throwable t) {
                // Manejar errores de red
                //txt.setText(t.getMessage());
            }
        });
    }
}