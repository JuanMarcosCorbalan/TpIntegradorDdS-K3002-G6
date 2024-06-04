package org.example.Formas_contribucion;


import java.util.Date;

public class Donacion_dinero extends Contribucion{
    Integer monto;
    Tipos_frecuencia frecuencia;

    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia) {
        this.monto = monto;
        this.frecuencia = frecuencia;
    }
    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia, Date fechaContribucion) {
        super(fechaContribucion);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

}
