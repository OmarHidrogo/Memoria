package com.omar_hidrogo_local.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generarArchivo(View v){
        try{
            EditText etNombre = (EditText) findViewById(R.id.etNombre);
            String nombre = etNombre.getText().toString();
            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            etNombre.setText("");

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Hubo un error  en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarPreferencia(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = miPreferenciaCompartida.edit();
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        EditText etCorreo = (EditText) findViewById(R.id.etCorreo);

        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();

        edit.putString("nombre", nombre);
        edit.putString("correo", correo);

        edit.commit();

        Toast.makeText(MainActivity.this, " Se ha creado la Preferencia Compartida", Toast.LENGTH_SHORT).show();
        etNombre.setText("");
        etCorreo.setText("");
    }

    public void mostrarPreferencia(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombre = miPreferenciaCompartida.getString("nombre","No existe esa variable");
        String correo = miPreferenciaCompartida.getString("correo","No existe esa variable");

        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.tvPreferenciaCompartida);

        String preferencia = "\nNombre: "+ nombre + "\nCorreo: "+ correo;

        tvPreferenciaCompartida.setText(preferencia);

    }


}
