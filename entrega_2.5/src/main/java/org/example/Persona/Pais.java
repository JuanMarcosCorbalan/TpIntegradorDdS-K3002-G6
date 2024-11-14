package org.example.Persona;

import javax.persistence.*;
import java.util.List;
@Entity
public class Pais {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    public String nombre;

    @OneToMany (mappedBy = "pais")
    private List<Ciudad> ciudades;
}
