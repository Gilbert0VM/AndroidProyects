package com.example.loginbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario, password;
    Button ingresar;
    String usu, pass;
    Toast mensaje;
    Boolean existe = false;
    Intent i;
    String[][] nombres = {{"juan","123"},{"maria","12345"},{"luis"},{"12341"}};

    /*Un toast es un mensaje que se muestra en la pantalla durante unos segundos*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ingresa tus credenciales");
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        ingresar = (Button) findViewById(R.id.ingresar);
        //Implementamos onClick listener aqui pq solo es para un boton
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usu = usuario.getText().toString();
                pass = password.getText().toString();
                //notificaciones popup rapida (LONG O SHORT)
                //mensaje = Toast.makeText(MainActivity.this,"hola",Toast.LENGTH_LONG);

                //hace una busqueda dentro del array para validar que el usuario existe
                for(int c=0;c<nombres.length; c++) {
                    if(usu.equals(nombres[c][0]) && pass.equals(nombres[c][1])) {
                        existe = true;
                    }
                }
                //si el usuario existe
                if(existe) {
                    mensaje = Toast.makeText(MainActivity.this,"Bienvenido", Toast.LENGTH_LONG);
                    i = new Intent(MainActivity.this,inicio.class);
                    i.putExtra("usuario",usu);
                    i.putExtra("pass",pass);
                    startActivity(i);
                    existe=false;
                }else {
                    mensaje = Toast.makeText(MainActivity.this,"Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
                }
                mensaje.show();
            }
        });
    }
}