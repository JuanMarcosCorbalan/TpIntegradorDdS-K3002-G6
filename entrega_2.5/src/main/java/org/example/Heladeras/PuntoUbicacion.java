package org.example.Heladeras;

import java.util.List;
import java.util.ArrayList;

public class PuntoUbicacion {
    Integer latitud;
    Integer longitud;
    String direccion;
    String nombre;

    List<Heladera> heladeras = new ArrayList<Heladera>();

    public PuntoUbicacion(String direccion) {
        this.direccion = direccion;
    }

    public PuntoUbicacion(Integer latitud, Integer longitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public void aniadirHeladera(Heladera heladera){
        heladeras.add(heladera);
    }
    public void quitarHeladera(Heladera heladera){
        heladeras.remove(heladera);
    }

    public Integer getLatitud() {return latitud;}
    public Integer getLongitud() {return longitud;}


}
