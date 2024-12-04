package org.example.Sistema;

import org.example.Persona.Persona;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //(cascade = CascadeType.PERSIST)
    Persona persona;

    String nombre;

    String contrasenia;

    public Usuario() {}

    public Usuario(Persona persona, String nombre, String contrasenia) {
        this.persona = persona;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

}
