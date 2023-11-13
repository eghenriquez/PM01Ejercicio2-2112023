package com.example.pm01ejercicio22112023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnconsulta, btnlista, btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnconsulta=(Button) findViewById(R.id.btnconsulta);
        btnlista=(Button) findViewById(R.id.btnlista);
        btnsalir=(Button) findViewById(R.id.btnsalir);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*crear intent para llamar a la actividad de consulta para consumir Api Json*/
                Intent intentconsulta = new Intent(getApplicationContext(), ActivityConsulta.class);
                startActivity(intentconsulta);
            }
        });

        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*crear intent para llamar a la actividad de visualizar lista api Json*/
                Intent intentlista = new Intent(getApplicationContext(), ActivityLista.class);
                startActivity(intentlista);
            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*crear intent para llamar a la actividad de salir del programa*/
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}