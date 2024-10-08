package org.example.Formas_contribucion;

import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import java.util.Date;


public class Distribucion_viandas extends Contribucion{
    Heladera heladera_origen;
    Heladera heladera_destino;
    Integer cantidad_viandas_a_mover;
    Vianda viandas[];
    Motivo_distribucion motivo_distribucion;


    public Integer getCantidad_viandas_a_mover() {
        return cantidad_viandas_a_mover;
    }

    public Distribucion_viandas(Integer cantidad_viandas_a_mover, Date fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidad_viandas_a_mover = cantidad_viandas_a_mover;
    }

    @Override
    public double calcular_puntos() {
        return cantidad_viandas_a_mover*ConstCalculo.VIANDAS_DISTRIBUIDAS.getValor();
    }
}
