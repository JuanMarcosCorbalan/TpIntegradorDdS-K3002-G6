package org.example.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class Localidad {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "localidad_ciudad")
    private Ciudad ciudad;

    @OneToMany (mappedBy = "localidad")
    public List<Domicilio> domicilios;

    String nombre;
}
