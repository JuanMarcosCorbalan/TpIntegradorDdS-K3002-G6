package org.example.Tarjetas;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudWeb {
    String IdSolicitud;
    org.example.Colaborador.Colaborador Colaborador;
    MotivoSolicitud Motivo;
    LocalDate FechaSolicitud;
    LocalTime HoraSolicitud;
    Heladera Heladera;
    Boolean Finalizada;


    public SolicitudWeb(org.example.Colaborador.Colaborador colaborador, MotivoSolicitud motivo, LocalDate fechaSolicitud, LocalTime horaSolicitud, org.example.Heladeras.Heladera heladera) {
        //IdSolicitud = calculadorIdSolicitudWeb.generarId();
        Colaborador = colaborador;
        Motivo = motivo;
        FechaSolicitud = fechaSolicitud;
        HoraSolicitud = horaSolicitud;
        Heladera = heladera;
        Finalizada = false;
    }
}
