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
    /*@OneToMany (mappedBy = "ciudad", cascade = CascadeType.ALL)
    public List<Localidad> localidades;*/
}
