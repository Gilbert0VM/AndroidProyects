package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // Declaración de variables
    Spinner estaciones;
    Button boton;
    TextView EstacionNombre, HoraNombre, TemperaturaNombre, HumedadNombre, EstadoNombre;
    String[] Estaciones = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36"}; // Tu array de estaciones
    boolean InfoClimaDescargadas = false;
    String[] EstacionS, HoraS, TemperaturaS, HumedadS, EstadoS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estaciones = findViewById(R.id.estaciones);
        boton = findViewById(R.id.boton);
        boton.setOnClickListener(this);

        new DownloadExchangeRatesTask().execute("https://api.gael.cloud/general/public/clima");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Estaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estaciones.setAdapter(adapter);
        estaciones.setOnItemSelectedListener(this);

        EstacionNombre = findViewById(R.id.EstacionNombre);
        HoraNombre = findViewById(R.id.HoraNombre);
        TemperaturaNombre = findViewById(R.id.TemperaturaNombre);
        HumedadNombre = findViewById(R.id.HumedadNombre);
        EstadoNombre = findViewById(R.id.EstadoNombre);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.boton) {
            if (InfoClimaDescargadas) {
                imprimirInfo();
            }
        }
    }

    private void imprimirInfo() {
        int indiceSpinner = estaciones.getSelectedItemPosition();
        EstacionNombre.setText(EstacionS[indiceSpinner]);
        HoraNombre.setText(HoraS[indiceSpinner]+" A. M.");
        TemperaturaNombre.setText(TemperaturaS[indiceSpinner]+ " Cº");
        HumedadNombre.setText(HumedadS[indiceSpinner]+ " %");
        EstadoNombre.setText(EstadoS[indiceSpinner]);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private class DownloadExchangeRatesTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonData) {
            if (jsonData != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonData);
                    EstacionS    = new String[jsonArray.length()];
                    HoraS        = new String[jsonArray.length()];
                    TemperaturaS = new String[jsonArray.length()];
                    HumedadS     = new String[jsonArray.length()];
                    EstadoS      = new String[jsonArray.length()];

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        EstacionS[i]    = jsonObject.getString("Estacion");
                        HoraS[i]        = jsonObject.getString("HoraUpdate");
                        TemperaturaS[i] = jsonObject.getString("Temp");
                        HumedadS[i]     = jsonObject.getString("Humedad");
                        EstadoS[i]      = jsonObject.getString("Estado");
                    }

                    InfoClimaDescargadas = true;
                    imprimirInfo();
                } catch (JSONException e) {
                    String ARG;
                    Log.d("ARG", e + "");
                }
            } else {
                Toast.makeText(getApplicationContext(), "Error al descargar datos", Toast.LENGTH_SHORT).show();
            }
 }
}
}