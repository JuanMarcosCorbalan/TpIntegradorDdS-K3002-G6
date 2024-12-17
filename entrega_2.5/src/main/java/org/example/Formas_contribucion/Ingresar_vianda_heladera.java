package org.example.Formas_contribucion;

import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import java.io.IOException;


public class Ingresar_vianda_heladera extends Contribucion{
    Vianda vianda_ingresada;
    Heladera heladera_destino;

    public void ingresar_vianda() throws IOException {
        heladera_destino.aniadirVianda(vianda_ingresada);
    }

    @Override
    public double calcular_puntos() {
        return super.calcular_puntos();
    }
}
