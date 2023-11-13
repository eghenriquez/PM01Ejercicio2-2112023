package com.example.pm01ejercicio22112023.Configuracion;

import com.example.pm01ejercicio22112023.Cuentas.Cuentas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Configuracion {

    String Ruta0  = "/posts";
    String Ruta1 = "/posts/{valor}";

    @GET(Ruta0)
    Call<List<Cuentas>> getUsuarios();

    @GET(Ruta1)
    Call<Cuentas> getUsuario(@Path("valor") String valor);
}
