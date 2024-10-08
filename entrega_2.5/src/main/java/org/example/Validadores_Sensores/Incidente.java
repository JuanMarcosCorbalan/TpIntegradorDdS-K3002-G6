package org.example.Validadores_Sensores;

import org.example.Heladeras.Heladera;

import java.time.LocalDate;
import java.time.LocalTime;

public class Incidente {
    Heladera heladera;
    LocalDate fecha;
    LocalTime hora;
    TipoIncidente tipoIncidente;

    public Incidente(Heladera heladera, TipoIncidente tipoIncidente) {
        this.heladera = heladera;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = tipoIncidente;
    }


    public Incidente() {

    }
}
