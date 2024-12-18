package org.example.Ofertas;

import org.example.Colaborador.Colaborador;
import org.example.GeneradorId;

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
    String codigo_canje;

    @ManyToOne
    Colaborador colaborador;

    public OfertaCanjeada() {}
    public OfertaCanjeada(String nombre, int puntos, LocalDate fecha,Colaborador colaborador) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.fecha = fecha;
        this.colaborador = colaborador;
        this.codigo_canje = GeneradorId.generar();
    }


}
