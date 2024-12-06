package org.example.Formas_contribucion;


import org.example.Colaborador.Colaborador;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Donacion_dinero extends Contribucion{

    Integer monto;

    @Enumerated(EnumType.STRING)
    private Tipos_frecuencia frecuencia;

    public Donacion_dinero() {

    }

    public Integer getMonto() {
        return monto;
    }

    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia, Colaborador colaborador) {
        super(LocalDate.now());
        this.monto = monto;
        this.frecuencia = frecuencia;
        this.colaborador = colaborador;
        this.contribucionExitosa = true;
        this.contribucionTerminada = true;
    }
    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia, LocalDate fechaContribucion) {
        super(fechaContribucion);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }
    /*
    public void realizarDonacion(){
        colaborador.pagar(monto);
    } */

    @Override
    public double calcular_puntos() {
        return monto*ConstCalculo.PESOS_DONADOS.getValor();
    }
}
