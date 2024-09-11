package org.example.Heladeras;

import org.example.PersonaVulnerable.RetiroVianda;
import org.example.Suscripcion.AdministradorSuscripciones;
import org.example.Suscripcion.AvisoPorDesperfecto;
import org.example.Suscripcion.FaltanNViandas;
import org.example.Suscripcion.QuedanNViandas;
import org.example.Suscripcion.Suscripcion;
import org.example.Validadores_Sensores.Validador;
import org.example.Heladeras.EstadoHeladera;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Heladera {
    PuntoUbicacion puntoUbicacion;
    String idHeladera;
    int unidadViandasActual;
    int unidadesMaximoViandas; //VIENE DEFINIDA
    List<Vianda> viandas = new ArrayList<Vianda>();
    Date FechaFuncionamiento;
    EstadoHeladera estado_actual = EstadoHeladera.INACTIVO; //ACTUALIZACION ENTREGA 2
    List<RetiroVianda> retiros = new ArrayList<RetiroVianda>();
    List<Validador> validadores = new ArrayList<Validador>();
    int temperaturaMaxima;
    int temperaturaMinima;
    AdministradorSuscripciones admin_suscr = new AdministradorSuscripciones();
    int cantidadFallas;
    int cantidadViandasDonadas;

    public Heladera(String idHeladera) {
        this.idHeladera = idHeladera;
    }

    public boolean tieneViandas(){
        return !viandas.isEmpty();
    }

    public void ponerFuncionamiento(){
        estado_actual = EstadoHeladera.ACTIVA;
    }
    public void solicitarMantenimiento(){
        estado_actual = EstadoHeladera.EN_MANTENIMIENTO;
    }
    public boolean requiereMantenimiento(){
        // verificacion sobre los parametros que indiquen si necesita mantenimiento
        return false;
    }
    public void aniadirVianda(Vianda unaVianda){
        viandas.add(unaVianda);
        this.notificar_viandas_faltantes();
        this.notificar_viandas_sobrantes();
    }

    public void quitarVianda(){
        viandas.removeFirst();
        this.notificar_viandas_sobrantes();
        this.notificar_viandas_faltantes();
    }
    public void definirTemperatura(int temperaturaMinima, int temperaturaMaxima){
        this.setTemperaturaMaxima(temperaturaMaxima);
        this.setTemperaturaMinima(temperaturaMinima);
    }

    //NUEVO ENTREGA 2
    public void aniadir_retiro(RetiroVianda retiro){
        retiros.add(retiro);
    }

    //SUFRE DESPERFECTO NOTIFICAR, CON EVENTTPYRE "desperfecto"
    public Integer cantidadViandasRetiradas(){
        return retiros.size();
    }

    public void aniadirDonacion(){
        cantidadViandasDonadas++;
    }


    // GETTERS Y SETTERS


    public String getIdHeladera() {
        return idHeladera;
    }

    public Integer getCantidadFallas(){
        return cantidadFallas;
    }

    public int getCantidadViandasDonadas() {
        return cantidadViandasDonadas;
    }

    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(int temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(int temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public PuntoUbicacion getPuntoUbicacion() {
        return puntoUbicacion;
    }

    public void setPuntoUbicacion(PuntoUbicacion puntoUbicacion) {
        this.puntoUbicacion = puntoUbicacion;
    }

    public void desactivar(){this.estado_actual = EstadoHeladera.INACTIVO;}
    public void activar(){this.estado_actual = EstadoHeladera.ACTIVA;}

    public AdministradorSuscripciones getAdmin_suscr() {return admin_suscr;}

    public int getViandasActuales(){return viandas.size();}

    public void notificar_viandas_sobrantes() {
        int viandasActuales = getViandasActuales();
        admin_suscr.notificar("Quedan"+Integer.toString(viandasActuales)+"Viandas");
    }
    public void notificar_viandas_faltantes() {
        Integer viandasActuales = getViandasActuales();
        int viandas_faltantes = (Integer) unidadesMaximoViandas - viandasActuales;
        admin_suscr.notificar("Faltan"+Integer.toString(viandas_faltantes) +"Viandas");
    }
    public void notificar_desperfecto()
    {
        admin_suscr.notificar("Aviso-Desperfecto");
    }
}
