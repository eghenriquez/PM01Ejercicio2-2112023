package com.example.pm01ejercicio22112023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm01ejercicio22112023.Configuracion.Configuracion;
import com.example.pm01ejercicio22112023.Cuentas.Cuentas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityLista extends AppCompatActivity {

    ListView lista;
    ArrayList<String> titulos = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    EditText txtlista;
    Button btnregresa2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        obtenerInfo();
        txtlista = (EditText) findViewById(R.id.txtlista);
        btnregresa2 = (Button) findViewById(R.id.btnregresa2);
        lista = (ListView) findViewById(R.id.lista);

        txtlista.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    buscarInfo(txtlista.getText().toString());
                    if (txtlista.getText().toString().equals("")) {
                        obtenerInfo();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Valor invalido", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnregresa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*crear intent para regresar a la actividad principal*/
                Intent intentregresa2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentregresa2);
            }
        });
    }

    /*funciones privadas de obtener informacion del Json Api */

    private void obtenerInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Configuracion interfaceusers = retrofit.create(Configuracion.class);

        Call<List<Cuentas>> llamada = interfaceusers.getUsuarios();

        llamada.enqueue(new Callback<List<Cuentas>>() {
            @Override
            public void onResponse(Call<List<Cuentas>> call, Response<List<Cuentas>> response) {
                titulos.clear();
                for (Cuentas cuentas : response.body()) {
                    titulos.add(cuentas.getTitle());

                    Log.i("On Response", cuentas.getTitle());
                }

                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titulos);
                lista.setAdapter(arrayAdapter);
                //notifica si la data ha cambiado
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cuentas>> call, Throwable t) {

            }
        });

    }

    private void buscarInfo(String valor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Configuracion interfaceusers = retrofit.create(Configuracion.class);


        Call<Cuentas> llamada = interfaceusers.getUsuario(valor);

        llamada.enqueue(new Callback<Cuentas>() {
            @Override
            public void onResponse(Call<Cuentas> call, Response<Cuentas> response) {


                try {
                    Cuentas cuentas = response.body();

                    titulos.clear();//eliminar todo los registros
                    titulos.add(cuentas.getTitle());
                    arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titulos);
                    lista.setAdapter(arrayAdapter);

                    //Log.i("On Response", usuarios.getTitle());

                    //notifica si la data ha cambiado
                    arrayAdapter.notifyDataSetChanged();
                } catch (Exception ex) {
                    titulos.clear();
                    txtlista.setText("");
                    obtenerInfo();
                    Toast.makeText(getApplicationContext(), "Valor no encontrado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Cuentas> call, Throwable t) {

            }
        });
    }
}
