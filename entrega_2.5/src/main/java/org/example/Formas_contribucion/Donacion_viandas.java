package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Donacion_viandas extends Contribucion{
    Heladera heladera;
    boolean entregada;
    List<Vianda> viandas = new ArrayList<Vianda>();
    Vianda vianda; // en un inicio creo que son donaciones unitarias de viandas

    public Integer cant_viandas()
    {
        return viandas.size();
    }

    public Donacion_viandas(Heladera heladera, boolean entregada, List<Vianda> viandas, Date fecha_contribucion) {
        super(fecha_contribucion);
        this.heladera = heladera;
        this.entregada = entregada;
        this.viandas = viandas;
    }

    public Donacion_viandas(Date fecha_contribucion) {
        super(fecha_contribucion);
    }

    public Donacion_viandas(Colaborador colaborador, Heladera heladera, Vianda vianda){
        super(colaborador);
        this.heladera = heladera;
        this.vianda = vianda;
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
}
