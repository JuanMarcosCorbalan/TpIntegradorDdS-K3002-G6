package org.example.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "ciudad_pais")
    public Pais pais;

    @OneToMany (mappedBy = "ciudad", cascade = CascadeType.ALL)
    public List<Localidad> localidades;
}
