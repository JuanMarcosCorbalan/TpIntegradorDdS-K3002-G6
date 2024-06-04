package org.example.Heladeras;

import org.example.PersonaVulnerable.RetirarVianda;
import org.example.PersonaVulnerable.RetiroVianda;
import org.example.Validadores_Sensores.Validador;
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
    }
    public void quitarVianda(Vianda unaVianda){
        viandas.remove(unaVianda);
    }
    public void quitarVianda(){
        viandas.removeFirst();
    }
    public void definirTemperatura(int temperaturaMinima, int temperaturaMaxima){
        this.setTemperaturaMaxima(temperaturaMaxima);
        this.setTemperaturaMinima(temperaturaMinima);
    }

    //NUEVO ENTREGA 2
    public void aniadir_retiro(RetiroVianda retiro){
        retiros.add(retiro);
    }


    // GETTERS Y SETTERS

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

}
