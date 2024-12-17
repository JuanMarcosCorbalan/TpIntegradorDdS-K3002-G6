package org.example.Conversores;

import org.example.Formas_contribucion.EstadoContribucion;
import org.example.Formas_contribucion.Motivo_distribucion;

import java.time.LocalDate;

public class DistribucionViandaHistorial {
    EstadoContribucion estado;
    int viandasAMover;
    Motivo_distribucion motivo;
    String heladera_origen;
    String heladera_destino;
    LocalDate fecha;

    public DistribucionViandaHistorial(EstadoContribucion estado,int viandasAMover,Motivo_distribucion motivo,String heladera_origen,String heladera_destino,LocalDate fecha ) {
        this.estado = estado;
        this.viandasAMover = viandasAMover;
        this.motivo = motivo;
        this.heladera_origen = heladera_origen;
        this.heladera_destino = heladera_destino;
        this.fecha = fecha;
    }



}
