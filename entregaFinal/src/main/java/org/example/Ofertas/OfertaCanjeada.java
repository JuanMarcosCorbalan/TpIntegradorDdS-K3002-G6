package org.example.Ofertas;

import org.example.Colaborador.Colaborador;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class OfertaCanjeada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;
    int puntos;
    LocalDate fecha;

    @ManyToOne
    Colaborador colaborador;

    String codigoUnico;

    public OfertaCanjeada() {}
    public OfertaCanjeada(String nombre, int puntos, LocalDate fecha,Colaborador colaborador) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.fecha = fecha;
        this.colaborador = colaborador;
        this.codigoUnico = GeneradorCodigoCanje.generarCodigoUnico();
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }
}
