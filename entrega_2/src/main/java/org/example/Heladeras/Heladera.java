package org.example.Heladeras;

import org.example.Validadores_Sensores.Validador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Heladera {
    PuntoUbicacion puntoUbicacion;
    String idHeladera;
    int unidadViandasActual;
    int unidadesMaximoViandas;
    List<Vianda> viandas = new ArrayList<Vianda>();
    Date FechaFuncionamiento;
    Boolean estaActiva;
    Boolean enMantenimiento;
    List<Validador> validadores = new ArrayList<Validador>();
    int temperaturaMaxima;
    int temperaturaMinima;

    public boolean tieneViandas(){
        return !viandas.isEmpty();
    }

    public void ponerFuncionamiento(){
        estaActiva = true;
        enMantenimiento = false;
    }
    public void solicitarMantenimiento(){
        enMantenimiento = true;
        // tiene que dejar de estar activa?
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
