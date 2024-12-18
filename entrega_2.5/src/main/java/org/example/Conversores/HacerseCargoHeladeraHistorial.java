package org.example.Conversores;

import org.example.Formas_contribucion.EstadoContribucion;
import org.example.Formas_contribucion.Motivo_distribucion;
import org.example.Heladeras.EstadoHeladera;

import java.time.LocalDate;

public class HacerseCargoHeladeraHistorial {
    String heladera;
    LocalDate fecha_contribucion;
    EstadoHeladera estado;

    public HacerseCargoHeladeraHistorial(String heladera,LocalDate fecha_contribucion,EstadoHeladera estado ) {
        this.heladera = heladera;
        this.fecha_contribucion = fecha_contribucion;
        this.estado = estado;
    }
}
