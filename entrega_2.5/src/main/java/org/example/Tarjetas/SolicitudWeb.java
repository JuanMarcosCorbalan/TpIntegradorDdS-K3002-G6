package org.example.Tarjetas;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class SolicitudWeb {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    /*RELACION CON HELADERA*/
    @ManyToOne
    @JoinColumn (name = "solicitud_web_heladera")
    public Heladera Heladera;

    @ManyToOne
    @JoinColumn (name = "solicitud_web_colaborador")
    public Colaborador Colaborador;

    @ManyToOne
    @JoinColumn (name = "solicitud_web_tarjeta")
    public TarjetaColaborador Tarjeta;

    @Enumerated(EnumType.STRING)
    MotivoSolicitud Motivo;

    LocalDate FechaSolicitud;
    LocalTime HoraSolicitud;
    Boolean Finalizada;


    public SolicitudWeb(Colaborador colaborador, MotivoSolicitud motivo, LocalDate fechaSolicitud, LocalTime horaSolicitud, Heladera heladera,TarjetaColaborador tarjeta) {
        //IdSolicitud = calculadorIdSolicitudWeb.generarId();
        Colaborador = colaborador;
        Motivo = motivo;
        FechaSolicitud = fechaSolicitud;
        HoraSolicitud = horaSolicitud;
        Heladera = heladera;
        Tarjeta = tarjeta;
        Finalizada = false;
    }

    public SolicitudWeb() {

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
