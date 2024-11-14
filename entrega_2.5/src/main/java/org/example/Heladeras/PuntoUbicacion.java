package org.example.Heladeras;

import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class PuntoUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String latitud;
    String longitud;
    String direccion;
    String nombre;

    @ManyToOne
    Localidad localidad;

    @OneToMany(mappedBy = "puntoUbicacion")
    List<Heladera> heladeras = new ArrayList<Heladera>();

    public PuntoUbicacion(String direccion) {
        this.direccion = direccion;
    }

    public PuntoUbicacion(String latitud, String longitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public PuntoUbicacion() {

    }

    public void aniadirHeladera(Heladera heladera){
        heladeras.add(heladera);
    }
    public void quitarHeladera(Heladera heladera){
        heladeras.remove(heladera);
    }

    public String getLatitud() {return latitud;}
    public String getLongitud() {return longitud;}


}
