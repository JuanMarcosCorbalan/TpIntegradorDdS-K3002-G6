package org.example.Formas_contribucion;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Donacion_dinero extends Contribucion{

    Integer monto;

    @Enumerated(EnumType.STRING)
    private Tipos_frecuencia frecuencia;

    public Integer getMonto() {
        return monto;
    }

    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia) {
        this.monto = monto;
        this.frecuencia = frecuencia;
    }
    public Donacion_dinero(Integer monto, Tipos_frecuencia frecuencia, LocalDate fechaContribucion) {
        super(fechaContribucion);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

    @Override
    public double calcular_puntos() {
        return monto*ConstCalculo.PESOS_DONADOS.getValor();
    }
}
