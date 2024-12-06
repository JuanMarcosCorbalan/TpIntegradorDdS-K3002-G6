package org.example.Tarjetas;

import org.example.Formas_contribucion.Contribucion;
import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.Donacion_viandas;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;
import org.example.Colaborador.Colaborador;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

import static org.example.Tarjetas.MotivoSolicitud.APERTURA_DISTRIBUCION;
import static org.example.Tarjetas.MotivoSolicitud.APERTURA_DONACION;

@Entity
public class TarjetaColaborador extends Tarjeta{
    //String IdTarjeta;
    //Colaborador colaborador;

    @OneToMany(mappedBy = "Colaborador")
    List<SolicitudWeb> solicitudesWeb = new ArrayList<SolicitudWeb>();

    @OneToMany(mappedBy = "Colaborador")
    List<SolicitudApertura> solicitudAperturas = new ArrayList<SolicitudApertura>();


    public TarjetaColaborador(Colaborador colaborador) {
        super(colaborador);
    }

    public TarjetaColaborador(String id,Colaborador colaborador) {
        super(colaborador);
    }

    public TarjetaColaborador() {

    }


    //public TarjetaColaborador() {}

    public void crearSolicitudWebDonacion(Heladera heladera){
        SolicitudWeb nuevaSolicitudWeb = new SolicitudWeb(colaborador, APERTURA_DONACION, LocalDate.now(), LocalTime.now(),heladera,this);
        solicitudesWeb.add(nuevaSolicitudWeb);
    }
    public SolicitudApertura crearSolicitudApertura(Heladera heladera){
        return new SolicitudApertura(heladera, LocalDate.now(), LocalTime.now(), colaborador,this);
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
        SolicitudWeb nuevaSolicitudWebOrigen = new SolicitudWeb(colaborador, APERTURA_DISTRIBUCION, LocalDate.now(), LocalTime.now(),heladeraOrigen,this);
        solicitudesWeb.add(nuevaSolicitudWebOrigen);
        SolicitudWeb nuevaSolicitudWebDestino = new SolicitudWeb(colaborador, APERTURA_DISTRIBUCION, LocalDate.now(), LocalTime.now(),heladeraDestino,this);
        solicitudesWeb.add(nuevaSolicitudWebDestino);
    }

    public Boolean duracionMenorA3Horas(SolicitudWeb solicitudWeb){
        Duration duracion = Duration.between(solicitudWeb.getHoraSolicitud() ,LocalTime.now());
        return duracion.compareTo(Duration.ofHours(3)) <= 0;
    }

    public void verificarApertura(Heladera heladera, Contribucion contribucion, SolicitudApertura solicitudApertura){
        // pregunta si es una instancia de donacion de viandas, si no esta finalizada y si el horario es el adecuado
        if (contribucion instanceof Donacion_viandas donacionActual) {
            if (!donacionActual.isContribucionFinalizada()) {
                // si la heladera de la contribucion es la actual
                if (donacionActual.getHeladera().equals(heladera)) {
                    // si existe una solicitud que matchee con la heladera
                    SolicitudWeb solicitudActual = this.buscarSolicitudValida(heladera);
                    if (solicitudActual != null) {
                        if (!heladera.estaLlena()) {
                            donacionActual.realizar_contribucion();
                            donacionActual.setContribucionExitosa(true);
                            donacionActual.setContribucionFinalizada(true);
                            solicitudApertura.setAperturaExitosa(true);

                        } else {
                            donacionActual.setContribucionExitosa(false);
                            donacionActual.setContribucionFinalizada(true);
                            solicitudApertura.setAperturaExitosa(false);
                        }
                        solicitudActual.setFinalizada(true);
                    } solicitudApertura.setAperturaExitosa(false);
                } solicitudApertura.setAperturaExitosa(false);
            } solicitudApertura.setAperturaExitosa(false);
        }
        // si la contribucion encontrada es una distribucion de viandas
        if (contribucion instanceof Distribucion_viandas distribucionViandas) {
            if (!distribucionViandas.isContribucionFinalizada()) {
                // si la heladera origen de la contribucion es la actual
                if (distribucionViandas.getHeladeraOrigen().equals(heladera)) {
                    // si existe una solicitud que matchee con la heladera
                    SolicitudWeb solicitudActual = this.buscarSolicitudValida(heladera);
                    if (solicitudActual != null) {
                        if (heladera.tieneCantidadDeViandas(distribucionViandas.getCantidadViandasAMover())) {
                            //distribucionViandas.realizar_contribucion();
                            heladera.retirarViandas(distribucionViandas.getCantidadViandasAMover());
                            distribucionViandas.setRetiroExitoso(true);
                            solicitudApertura.setAperturaExitosa(true);
                            return;
                        } else {
                            distribucionViandas.setRetiroExitoso(false);
                            distribucionViandas.setContribucionFinalizada(true);
                            distribucionViandas.setContribucionExitosa(false);
                            solicitudApertura.setAperturaExitosa(false);
                        }
                        solicitudActual.setFinalizada(true);
                    } solicitudApertura.setAperturaExitosa(false);
                } solicitudApertura.setAperturaExitosa(false);
                // si la heladera destino de la contribucion es la actual
                if (distribucionViandas.getHeladeraDestino().equals(heladera)) {
                    // si existe una solicitud que matchee con la heladera
                    SolicitudWeb solicitudActual = this.buscarSolicitudValida(heladera);
                    if (solicitudActual != null) {
                        if (heladera.tieneEspacioDisponible(distribucionViandas.getCantidadViandasAMover())) {
                            //distribucionViandas.realizar_contribucion();
                            for (int i = 0; i < distribucionViandas.getCantidadViandasAMover(); i++) {
                                heladera.aniadirVianda(new Vianda("Vianda Distribuida"));
                            }
                            distribucionViandas.setIngresoExitoso(true);
                            solicitudApertura.setAperturaExitosa(true);
                            return;
                        } else {
                            distribucionViandas.setIngresoExitoso(false);
                            distribucionViandas.setContribucionFinalizada(true);
                            distribucionViandas.setContribucionExitosa(false);
                            solicitudApertura.setAperturaExitosa(false);
                        }
                        solicitudActual.setFinalizada(true);
                    } solicitudApertura.setAperturaExitosa(false);
                } solicitudApertura.setAperturaExitosa(false);
            } solicitudApertura.setAperturaExitosa(false);
        }

    }


}
