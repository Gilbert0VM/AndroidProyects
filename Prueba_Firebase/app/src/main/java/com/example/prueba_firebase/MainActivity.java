package com.example.prueba_firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba_firebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String texto1;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        txt = findViewById(R.id.textView);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Consulta para obtener todos los documentos de la colección

        b.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                texto1 = b.editText.getText().toString();

                Map<String, String> mensaje = new HashMap<>();
                mensaje.put("texto",texto1);

                db.collection("prueba")
                        .add(mensaje).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                update(db);
                                Toast.makeText(MainActivity.this,"Mensaje enviado",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG,"FATAL:" + e);
                            }
                        });

            }
        });
    }
    public void update(FirebaseFirestore db) {
        db.collection("prueba")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String contenido = "";
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Map<String, Object> data = document.getData();
                            String textoDocumento = data.get("texto").toString();
                            contenido += textoDocumento + "\n";
                        }

                        // Muestra los documentos en el TextView
                        txt.setText(contenido);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejo de errores en caso de que la consulta falle
                        Log.d(TAG,"" +e);
                        txt.setText("Error al obtener los documentos");
                    }
                });
    }
}