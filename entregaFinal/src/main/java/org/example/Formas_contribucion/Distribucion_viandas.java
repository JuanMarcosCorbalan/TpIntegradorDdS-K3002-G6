package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Distribucion_viandas extends Contribucion{

    @OneToOne(cascade = CascadeType.ALL)
    Heladera heladeraOrigen;

    @OneToOne(cascade = CascadeType.ALL)
    Heladera heladeraDestino;

    Integer cantidadViandasAMover;

    LocalDate fechaDistribucion;

    @Enumerated(EnumType.STRING)
    Motivo_distribucion motivo_distribucion;

    @OneToMany(mappedBy = "registro",cascade = CascadeType.ALL)
    List<Vianda> viandas = new ArrayList<Vianda>();

    // preguntar juan
    @Transient
    Boolean ContribucionFinalizada;
    @Transient
    Boolean ContribucionExitosa;
    @Transient
    Boolean RetiroExitoso;
    @Transient
    Boolean IngresoExitoso;

    public Distribucion_viandas() {

    }


    public Integer getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }

    public Distribucion_viandas(Colaborador colaborador, Integer cantidadViandasAMover, LocalDate fechaColaboracion) {
        super(colaborador, fechaColaboracion);
        this.cantidadViandasAMover = cantidadViandasAMover;
    }

    public Distribucion_viandas(Integer cantidadViandasAMover, LocalDate fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidadViandasAMover = cantidadViandasAMover;
    }
    public Distribucion_viandas(Integer cantidadViandasAMover, Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, Motivo_distribucion motivo_distribucion,LocalDate fecha_distribucion) {
        super(colaborador);
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.heladeraDestino = heladeraDestino;
        this.heladeraOrigen = heladeraOrigen;
        this.motivo_distribucion = motivo_distribucion;
        this.fecha_contribucion = LocalDate.now();
        this.estado = EstadoContribucion.EN_CURSO;
        this.fechaDistribucion = fecha_distribucion;
    }
    @Override
    public void realizar_contribucion(){
        super.realizar_contribucion();
        viandas = heladeraOrigen.obtenerViandasARetirar(cantidadViandasAMover);
        for(Vianda vianda : viandas) {
            vianda.setHeladera(heladeraDestino);
            heladeraDestino.aniadirVianda(vianda);
            heladeraDestino.aniadirDonacion();
        }
    }

    @Override
    public double calcular_puntos() {
        return cantidadViandasAMover *ConstCalculo.VIANDAS_DISTRIBUIDAS.getValor();
    }

    public Boolean isContribucionFinalizada() {
        return ContribucionFinalizada;
    }

    public Heladera getHeladeraDestino() {
        return heladeraDestino;
    }

    public Heladera getHeladeraOrigen() {
        return heladeraOrigen;
    }

    public void setContribucionExitosa(Boolean contribucionExistosa) {
        ContribucionExitosa = contribucionExistosa;
    }

    public void setContribucionFinalizada(Boolean contribucionFinalizada) {
        ContribucionFinalizada = contribucionFinalizada;
    }

    public void setRetiroExitoso(Boolean retiroExitoso) {
        RetiroExitoso = retiroExitoso;
    }

    public void setIngresoExitoso(Boolean ingresoExitoso) {
        IngresoExitoso = ingresoExitoso;
    }

    public Motivo_distribucion getMotivo() {
        return motivo_distribucion;
    }

    public LocalDate getFechaDistribucion() {
        return fechaDistribucion;
    }
}
