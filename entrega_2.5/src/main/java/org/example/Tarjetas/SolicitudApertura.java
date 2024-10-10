package org.example.Tarjetas;

import org.example.Heladeras.Heladera;
import org.example.Colaborador.Colaborador;


import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudApertura {
    Heladera Heladera;
    LocalDate Fecha;
    LocalTime Hora;
    Colaborador Colaborador;
    Boolean AperturaExitosa = null;

    public SolicitudApertura(org.example.Heladeras.Heladera heladera, LocalDate fecha, LocalTime hora, org.example.Colaborador.Colaborador colaborador) {
        Heladera = heladera;
        Fecha = fecha;
        Hora = hora;
        Colaborador = colaborador;
    }

    public Boolean getAperturaExitosa() {
        return AperturaExitosa;
    }

    public void setAperturaExitosa(Boolean aperturaExitosa) {
        AperturaExitosa = aperturaExitosa;
    }
}
