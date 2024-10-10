package org.example.Tarjetas;

import org.example.Heladeras.Heladera;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.Tarjetas.MotivoSolicitud.APERTURA_DONACION;

public class TarjetaColaborador {
    private String IdTarjeta;
    org.example.Colaborador.Colaborador colaborador;
    List<SolicitudWeb> solicitudesApertura = new ArrayList<SolicitudWeb>();
    List<SolicitudApertura> aperturas = new ArrayList<SolicitudApertura>();


    public void crearSolicitudWeb(Heladera heladera){
        SolicitudWeb nuevaSolicitudWeb = new SolicitudWeb(colaborador, APERTURA_DONACION, LocalDate.now(), LocalTime.now(),heladera);
    }
}
