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

    public Heladera getHeladera() {
        return Heladera;
    }

    public LocalDate getFechaSolicitud() {
        return FechaSolicitud;
    }

    public LocalTime getHoraSolicitud() {
        return HoraSolicitud;
    }

    public Boolean getFinalizada() {
        return Finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        Finalizada = finalizada;
    }
}
