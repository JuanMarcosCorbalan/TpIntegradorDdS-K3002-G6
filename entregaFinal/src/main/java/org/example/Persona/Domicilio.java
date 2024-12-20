package org.example.Persona;

import org.example.Heladeras.PuntoUbicacion;

import javax.persistence.*;
import java.util.List;

@Entity
public class Domicilio {
    String latitud;
    String longitud;

    /*****************************/
    //Persistencia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "domicilio_localidad")
    public Localidad localidad;

    @OneToMany (mappedBy = "domicilio")
    public List<Persona> personas;

    @OneToMany(mappedBy = "localidad")
    public List<PuntoUbicacion> puntoUbicaciones;

    /*******************************/
    String direccion;
    Boolean daALaCalle;

    public Domicilio (String latitud, String longitud, String direccion, Localidad localidad) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Domicilio () {}

    public Domicilio(Localidad localidad, String direccion) {
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Boolean getDaALaCalle() {
        return daALaCalle;
    }

    public void setDaALaCalle(Boolean daALaCalle) {
        this.daALaCalle = daALaCalle;
    }

    public String getDireccion() {
        return direccion;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public String getDireccionCompleta() {
        String nombre_ciudad = localidad.getCiudad().getNombre();
        String nombre_localidad = localidad.getNombre();
        String nombre_pais = localidad.getCiudad().getPais().getNombre();
        return direccion + ", " + nombre_localidad+ ", " + nombre_ciudad+ ", "  + nombre_pais;
    }
}
