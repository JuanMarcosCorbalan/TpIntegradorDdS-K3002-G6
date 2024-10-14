package org.example.Colaborador.ControladoresColaborador;

import io.javalin.http.Handler;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;


public class InicioSesionHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception{
        context.render("/paginaWebColaboracionHeladeras/inicioSesion/html/inicioSesion.mustache");
    }
}
