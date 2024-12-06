package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
public class Donacion_viandas extends Contribucion{

    @OneToOne(cascade = CascadeType.MERGE)
    Heladera heladera;

    @OneToOne(cascade = CascadeType.ALL)
    Vianda vianda; // en un inicio creo que son donaciones unitarias de viandas

    // ESTOS NOSE QUE ONDA
    @Transient
    List<Vianda> viandas = new ArrayList<Vianda>(); // NOSE Q ONDA

    boolean contribucionExitosa; // NOSE Q ONDA
    boolean contribucionFinalizada; // NOSE Q ONDA

    public Donacion_viandas() {

    }

    public Integer cant_viandas()
    {
        return viandas.size();
    }

    public Donacion_viandas(Heladera heladera, boolean contribucionExitosa, List<Vianda> viandas, LocalDate fecha_contribucion) {
        super(fecha_contribucion);
        this.heladera = heladera;
        this.contribucionExitosa = contribucionExitosa;
        this.viandas = viandas;
    }

    public Donacion_viandas(LocalDate fecha_contribucion) {
        super(fecha_contribucion);
    }

    public Donacion_viandas(Colaborador colaborador, Heladera heladera, Vianda vianda){
        super(colaborador);
        this.heladera = heladera;
        this.vianda = vianda;
        this.contribucionExitosa = false;
        this.contribucionFinalizada = false;
    }

    @Override
    public double calcular_puntos() {
        return cant_viandas()*ConstCalculo.VIANDAS_DONADAS.getValor();
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
}
