package org.example.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class Localidad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "localidad_ciudad")
    private Ciudad ciudad;

    public Localidad(String nombre_localidad, Ciudad ciudad) {
        this.nombre = nombre_localidad;
        this.ciudad = ciudad;
    }

    public Localidad() {}

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
