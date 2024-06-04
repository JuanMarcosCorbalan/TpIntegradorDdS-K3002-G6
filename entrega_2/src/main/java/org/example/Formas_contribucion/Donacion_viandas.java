package org.example.Formas_contribucion;

import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import java.util.Date;

public class Donacion_viandas extends Contribucion{
    Heladera heladera;
    boolean entregada;
    Vianda viandas[];

    public Integer cant_viandas()
    {
        return viandas.length;
    }

    public Donacion_viandas(Heladera heladera, boolean entregada, Vianda[] viandas, Date fecha_contribucion) {
        super(fecha_contribucion);
        this.heladera = heladera;
        this.entregada = entregada;
        this.viandas = viandas;
    }

    public Donacion_viandas(Date fecha_contribucion) {
        super(fecha_contribucion);
    }
}
