package org.example.Validadores_Sensores;

import org.example.Heladeras.Heladera;

import java.util.ArrayList;
import java.util.List;

public class Validador {
    Heladera heladera;
    List<Censado> censado = new ArrayList<>();
    List<Alerta> alertas = new ArrayList<>();

    public Validador(Heladera heladera) {this.heladera = heladera;}
    public void darAviso(TipoAlerta tipo){
        Alerta nueva_alerta = new Alerta(heladera,tipo);
        alertas.add(nueva_alerta);
    }

    //PARA PRUEBAS
    public Integer cantidadAlertas(){return alertas.size();}

    public List<Alerta> getAlertas(){return alertas;}
}
