package org.example.Persona;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Rol {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "rol_persona")
    public Persona persona;

    public Rol() {}

    public Persona getPersona() {
        return persona;
    };
}
