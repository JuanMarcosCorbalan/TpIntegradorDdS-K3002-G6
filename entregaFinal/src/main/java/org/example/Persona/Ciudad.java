package org.example.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "ciudad_pais")
    public Pais pais;

    public String nombre;


    public Ciudad(String nombreCiudad,Pais pais)
    {
        this.pais = pais;
        this.nombre = nombreCiudad;
    }

    public Ciudad() {}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Pais getPais() {
        return pais;
    }
}
