package com.example.examen.Interface;

import com.example.examen.Model.EarthquakeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EarthquakeService {
    @GET("2.5_week.geojson") // Reemplaza con la URL de tu API
    Call<EarthquakeResponse> getEarthquakeData();
}
// AQUI SE DEFINE LA INTERFACE CON LA QUE MANEJAREMOS LA RUTA DE LA API, EN ESTE CASO USAMOS LA SOLICITUD
// GET DEBIDO A QUE QUEREMOS OBTENER LOS DATOS