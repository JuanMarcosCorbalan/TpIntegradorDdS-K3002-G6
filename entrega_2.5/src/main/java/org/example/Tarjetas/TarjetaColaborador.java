package org.example.Tarjetas;

import org.example.Heladeras.Heladera;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

import static org.example.Tarjetas.MotivoSolicitud.APERTURA_DISTRIBUCION;
import static org.example.Tarjetas.MotivoSolicitud.APERTURA_DONACION;

public class TarjetaColaborador {
    private String IdTarjeta;
    org.example.Colaborador.Colaborador colaborador;
    List<SolicitudWeb> solicitudesWeb = new ArrayList<SolicitudWeb>();
    List<SolicitudApertura> solicitudAperturas = new ArrayList<SolicitudApertura>();


    public void crearSolicitudWebDonacion(Heladera heladera){
        SolicitudWeb nuevaSolicitudWeb = new SolicitudWeb(colaborador, APERTURA_DONACION, LocalDate.now(), LocalTime.now(),heladera);
        solicitudesWeb.add(nuevaSolicitudWeb);
    }
    public SolicitudApertura crearSolicitudApertura(Heladera heladera){
        return new SolicitudApertura(heladera, LocalDate.now(), LocalTime.now(), colaborador);
    }

    public SolicitudWeb buscarSolicitudValida(Heladera heladera){
        for(SolicitudWeb solicitudWeb: solicitudesWeb){
            // verifica las solicitudes para esa heladera, que tengan un tiempo menor a 3 horas solicitadas y
            // que sean del mismo dia
            if (solicitudWeb.getHeladera().equals(heladera) && this.duracionMenorA3Horas(solicitudWeb)
                    && LocalDate.now().isEqual(solicitudWeb.getFechaSolicitud()) && !solicitudWeb.getFinalizada()) {
                return solicitudWeb;
            }
        }
        return null;
    }

    public void crearSolicitudesWebDistribucion(Heladera heladeraOrigen, Heladera heladeraDestino){
        SolicitudWeb nuevaSolicitudWebOrigen = new SolicitudWeb(colaborador, APERTURA_DISTRIBUCION, LocalDate.now(), LocalTime.now(),heladeraOrigen);
        solicitudesWeb.add(nuevaSolicitudWebOrigen);
        SolicitudWeb nuevaSolicitudWebDestino = new SolicitudWeb(colaborador, APERTURA_DISTRIBUCION, LocalDate.now(), LocalTime.now(),heladeraDestino);
        solicitudesWeb.add(nuevaSolicitudWebDestino);
    }

    public Boolean duracionMenorA3Horas(SolicitudWeb solicitudWeb){
        Duration duracion = Duration.between(solicitudWeb.getHoraSolicitud() ,LocalTime.now());
        return duracion.compareTo(Duration.ofHours(3)) <= 0;
    }
}
