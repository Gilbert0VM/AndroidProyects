package com.example.examen.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.MainActivity;
import com.example.examen.Model.EarthquakeFeature;
import com.example.examen.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class alertAdapter extends RecyclerView.Adapter<alertAdapter.ViewHolder> {

    private List<EarthquakeFeature> ListaAlertas;
    int c;

    public alertAdapter(List<EarthquakeFeature> alertas, int c) {
        this.ListaAlertas = alertas;
        this.c = c;
    }

    @NonNull
    @Override
    public alertAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull alertAdapter.ViewHolder holder, int position) {
        EarthquakeFeature earthquake = ListaAlertas.get(position);
        double mag = earthquake.getProperties().getMag();
        String place = earthquake.getProperties().getPlace();
        long time = earthquake.getProperties().getTime();
        holder.bind(mag, place, time);
    }

    @Override
    public int getItemCount() {
        if(c==0) {
            return ListaAlertas.size();
        }else {
            return c;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMagnitud, tvFecha, tvLugar, title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvLugar = itemView.findViewById(R.id.tvLugar);
            tvMagnitud = itemView.findViewById(R.id.tvMagnitud);
        }

        public void bind(double mag, String place, long time) {
            if(mag <=3.5) {
                title.setText("Imperceptible");
                title.setTextColor(Color.rgb(49,181,76));
                tvMagnitud.setTextColor(Color.rgb(49,181,76));
            }else if(mag > 3.5 && mag<=5.4) {
                title.setText("Apenas perceptible");
                title.setTextColor(Color.rgb(126,191,66));
                tvMagnitud.setTextColor(Color.rgb(126,191,66));
            }else if(mag > 5.4 && mag<=6.0) {
                title.setText("¡Temblor perceptible!");
                title.setTextColor(Color.rgb(237,193,19));
                tvMagnitud.setTextColor(Color.rgb(237,193,19));
            }else if(mag > 6.0 && mag<=6.9) {
                title.setText("¡Temblor severo!");
                title.setTextColor(Color.rgb(249,154,26));
                tvMagnitud.setTextColor(Color.rgb(249,154,26));
            }else if(mag > 6.9 ) {
                title.setText("¡Terremoto!");
                title.setTextColor(Color.rgb(227,50,35));
                tvMagnitud.setTextColor(Color.rgb(227,50,35));
            }
            //tvMagnitud.setText(mag + "");
            tvFecha.setText(convert(time) + "");
            tvLugar.setText(place + "");
        }
        private String convert(long time) {
            Date date = new Date(time);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault());
            return sdf.format(date);
        }
    }
}
