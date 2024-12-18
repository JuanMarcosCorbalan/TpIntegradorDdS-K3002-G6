package org.example.Tarjetas;

import org.example.Heladeras.Heladera;
import org.example.Colaborador.Colaborador;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class SolicitudApertura {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "solicitud_apertura_heladera")
    public Heladera Heladera;

    @ManyToOne
    @JoinColumn (name = "solicitud_apertura_colaborador")
    public Colaborador Colaborador;

    @ManyToOne
    @JoinColumn (name = "solicitud_apertura_tarjeta")
    public TarjetaColaborador Tarjeta;


    LocalDate Fecha;
    LocalTime Hora;
    Boolean AperturaExitosa = null;

    public SolicitudApertura(Heladera heladera, LocalDate fecha, LocalTime hora, Colaborador colaborador, TarjetaColaborador tarjetaColaborador) {
        Heladera = heladera;
        Fecha = fecha;
        Hora = hora;
        Colaborador = colaborador;
        Tarjeta = tarjetaColaborador;
    }

    public SolicitudApertura() {

    }

    public Boolean getAperturaExitosa() {
        return AperturaExitosa;
    }

    public void setAperturaExitosa(Boolean aperturaExitosa) {
        AperturaExitosa = aperturaExitosa;
    }
}
