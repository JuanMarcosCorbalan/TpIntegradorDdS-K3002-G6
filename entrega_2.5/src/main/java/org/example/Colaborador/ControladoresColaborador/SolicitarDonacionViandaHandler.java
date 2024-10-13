package org.example.Colaborador.ControladoresColaborador;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class SolicitarDonacionViandaHandler implements Handler {
    public SolicitarDonacionViandaHandler() {
    }

    public static void realizarDonacion(Colaborador colaborador, String nombre, LocalDate fechaVencimiento, Heladera heladera, String calorias, String pesoGramos)
    {
        Vianda viandaADonar = new Vianda(nombre, fechaVencimiento, calorias, pesoGramos);
        colaborador.solicitarDonacionVianda(heladera, viandaADonar);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        context.render("/paginaWebColaboracionHeladeras/donacionVianda/html/donacionVianda.html");
    }
}
