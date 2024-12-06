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

    @Enumerated(EnumType.STRING)
    Motivo_distribucion motivo_distribucion;

    @OneToMany(mappedBy = "registro",cascade = CascadeType.ALL)
    List<Vianda> viandas = new ArrayList<Vianda>();

    // preguntar juan
    Boolean ContribucionFinalizada;
    Boolean ContribucionExitosa;
    Boolean RetiroExitoso;
    Boolean IngresoExitoso;

    public Distribucion_viandas() {

    }


    public Integer getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }

    public Distribucion_viandas(Integer cantidadViandasAMover, LocalDate fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidadViandasAMover = cantidadViandasAMover;
    }
    public Distribucion_viandas(Integer cantidadViandasAMover, Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, Motivo_distribucion motivo_distribucion) {
        super(colaborador);
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.heladeraDestino = heladeraDestino;
        this.heladeraOrigen = heladeraOrigen;
        this.motivo_distribucion = motivo_distribucion;
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
}
