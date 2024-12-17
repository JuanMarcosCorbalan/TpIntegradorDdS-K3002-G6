package org.example.Conversores;

import org.example.Formas_contribucion.Donacion_viandas;
import org.example.Formas_contribucion.EstadoContribucion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionViandaHistorial {
    String nombre_heladera;
    String nombre_vianda;
    LocalDate fecha_contribucion;
    EstadoContribucion estado;

    public DonacionViandaHistorial(String nombre_heladera,String nombre_vianda,EstadoContribucion estado_contribucion) {
        this.nombre_heladera = nombre_heladera;
        this.nombre_vianda = nombre_vianda;
        this.estado = estado_contribucion;
        this.fecha_contribucion = LocalDate.now();
    }
}
