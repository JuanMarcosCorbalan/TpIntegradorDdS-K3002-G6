package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

abstract public class Contribucion {
    Colaborador colaborador;
    Date fecha_contribucion;
    Boolean contribucionExitosa;
    Boolean contribucionTerminada;


    public void realizar_contribucion (){
        colaborador.agregarContribucion(this);
    };
    public void verificar_colaborador(Colaborador colaborador) {};
    public double calcular_puntos(){return 0.0;};

    public Contribucion(Date fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }

    public Contribucion(Colaborador colaborador){
        this.colaborador = colaborador;
    }
    public Contribucion() {
    }

    public Date getFecha_contribucion() {
        return fecha_contribucion;
    }

    public void setFecha_contribucion(Date fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }
}
