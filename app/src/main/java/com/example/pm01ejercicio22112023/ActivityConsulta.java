package com.example.pm01ejercicio22112023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityConsulta extends AppCompatActivity {

    EditText txtid, txttitulo, txtcuerpo;
    Button btnconsultar, btnregresa1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        txtid = findViewById(R.id.txtid);
        txttitulo = findViewById(R.id.txttitulo);
        txtcuerpo = findViewById(R.id.txtcuerpo);
        btnconsultar = findViewById(R.id.btnconsultar);
        btnregresa1 = findViewById(R.id.btnregresa1);

        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarWs();
            }
        });

        btnregresa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*crear intent para regresar a la actividad principal*/
                Intent intentregresa1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentregresa1);
            }
        });
    }

    private void ConsultarWs() {

        String url = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtid.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    txttitulo.setText(title);
                    txtcuerpo.setText(jsonObject.getString("body"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }
}
