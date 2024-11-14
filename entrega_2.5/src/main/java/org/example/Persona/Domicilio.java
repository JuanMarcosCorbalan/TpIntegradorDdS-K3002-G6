package org.example.Persona;

import org.example.Heladeras.PuntoUbicacion;

import javax.persistence.*;
import java.util.List;

@Entity
public class Domicilio {
    String latitud;
    String longitud;
    //Ciudad ciudad;

    /*****************************/
    //Persistencia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "domicilio_localidad")
    public Localidad localidad;

    @OneToMany (mappedBy = "domicilio")
    public List<Persona> personas;

    @OneToMany(mappedBy = "localidad")
    public List<PuntoUbicacion> puntoUbicaciones;

    /*******************************/


    String direccion;
    //Pais Pais;



    public Domicilio (String latitud, String longitud, String direccion, Localidad localidad) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Domicilio () {}

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}
