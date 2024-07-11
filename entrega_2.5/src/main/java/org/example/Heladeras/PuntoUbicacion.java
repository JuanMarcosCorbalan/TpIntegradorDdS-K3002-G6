package org.example.Heladeras;

import java.util.List;
import java.util.ArrayList;

public class PuntoUbicacion {
    String latitud;
    String longitud;
    String direccion;
    String nombre;

    List<Heladera> heladeras = new ArrayList<Heladera>();

    public void aniadirHeladera(Heladera heladera){
        heladeras.add(heladera);
    }
    public void quitarHeladera(Heladera heladera){
        heladeras.remove(heladera);
    }
}
