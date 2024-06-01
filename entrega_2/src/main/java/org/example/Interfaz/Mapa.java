package org.example.Interfaz;

import org.example.Heladeras.Heladera;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    List<Heladera> heladeras = new ArrayList<Heladera>();

    // creo que esto no va pero lo dejo por ahora
    public Heladera seleccionarHeladera(Heladera unaHeladera){
        Integer indiceHeladera = heladeras.indexOf(unaHeladera);
        if(indiceHeladera == -1){
            throw new Error("No existe esta heladera");
        }
        Heladera heladeraDeLista = heladeras.get(indiceHeladera);
        return heladeraDeLista;
    }
}
