package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import java.util.Date;


public class Distribucion_viandas extends Contribucion{
    Heladera heladeraOrigen;
    Heladera heladeraDestino;
    Integer cantidadViandasAMover;
//    Vianda viandas[];
    Motivo_distribucion motivo_distribucion;
    Boolean ContribucionFinalizada;
    Boolean ContribucionExitosa;
    Boolean RetiroExitoso;
    Boolean IngresoExitoso;



    public Integer getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }

    public Distribucion_viandas(Integer cantidadViandasAMover, Date fechaColaboracion) {
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
