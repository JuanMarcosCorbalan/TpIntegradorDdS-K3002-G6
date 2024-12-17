package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Contribucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    public Colaborador colaborador;

    public LocalDate fecha_contribucion;

    @Enumerated(EnumType.STRING)
    protected EstadoContribucion estado;

    @Transient
    Boolean contribucionExitosa;

    @Transient
    Boolean contribucionTerminada;


    public void realizar_contribucion (){
        colaborador.agregarContribucion(this);
    };
    public void verificar_colaborador(Colaborador colaborador) {};
    public double calcular_puntos(){return 0.0;};

    public Contribucion(LocalDate fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }

    public Contribucion(Colaborador colaborador){
        this.colaborador = colaborador;
    }
    public Contribucion() {
    }

    public LocalDate getFecha_contribucion() {
        return fecha_contribucion;
    }

    public void setFecha_contribucion(LocalDate fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }

    public EstadoContribucion getEstado() {
        return estado;
    }
}
