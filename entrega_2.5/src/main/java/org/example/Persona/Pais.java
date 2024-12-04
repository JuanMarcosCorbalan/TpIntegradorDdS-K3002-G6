package org.example.Persona;

import javax.persistence.*;
import java.util.List;
@Entity
public class Pais {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    public String nombre;

    public Pais(String NombrePais)
    {
        this.nombre = NombrePais;
    }

    public Pais() {}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
