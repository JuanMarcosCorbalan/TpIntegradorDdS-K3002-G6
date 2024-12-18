package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static org.example.Formas_contribucion.EstadoContribucion.EN_CURSO;

@Entity
public class Donacion_viandas extends Contribucion{

    @OneToOne(cascade = CascadeType.MERGE)
    Heladera heladera;

    @OneToOne(cascade = CascadeType.ALL)
    Vianda vianda; // en un inicio creo que son donaciones unitarias de viandas

    // ESTOS NOSE QUE ONDA
    @Transient
    List<Vianda> viandas = new ArrayList<Vianda>(); // NOSE Q ONDA

    Integer cantidadViandas;

    boolean contribucionExitosa; // NOSE Q ONDA
    boolean contribucionFinalizada; // NOSE Q ONDA

    public Donacion_viandas() {

    }

    public Integer cant_viandas()
    {
        return cantidadViandas;
    }

    public Donacion_viandas(Heladera heladera, boolean contribucionExitosa, List<Vianda> viandas, LocalDate fecha_contribucion) {
        super(fecha_contribucion);
        this.heladera = heladera;
        this.contribucionExitosa = contribucionExitosa;
        this.viandas = viandas;
    }

    public Donacion_viandas(Colaborador colaborador, LocalDate fecha_contribucion, Integer cantidad) {
        super(colaborador, fecha_contribucion);
        this.cantidadViandas = cantidad;
    }

    public Donacion_viandas(Colaborador colaborador, Heladera heladera, Vianda vianda){
        super(colaborador);
        this.heladera = heladera;
        this.vianda = vianda;
        this.estado = EN_CURSO;
        this.fecha_contribucion = LocalDate.now();
        this.cantidadViandas = 1;
    }

    @Override
    public double calcular_puntos() {
        return cantidadViandas*ConstCalculo.VIANDAS_DONADAS.getValor();
    }

    @Override
    public void realizar_contribucion(){
        super.realizar_contribucion();
        heladera.aniadirVianda(vianda);
        heladera.aniadirDonacion();
    }

    public Heladera getHeladera() {
        return heladera;
    }


    public boolean isContribucionExitosa() {
        return contribucionExitosa;
    }
    public boolean isContribucionFinalizada() {
        return contribucionFinalizada;
    }

    public void setContribucionExitosa(boolean contribucionExitosa) {
        this.contribucionExitosa = contribucionExitosa;
    }

    public void setContribucionFinalizada(boolean contribucionFinalizada) {
        this.contribucionFinalizada = contribucionFinalizada;
    }

    public Vianda getVianda() {
        return vianda;
    }
    public Boolean getContribucionFinalizada(){
        return contribucionFinalizada;
    }
    public Boolean getContribucionExitosa(){
        return contribucionExitosa;
    }
}
