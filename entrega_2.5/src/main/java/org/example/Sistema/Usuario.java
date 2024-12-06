package org.example.Sistema;

import org.example.Persona.Persona_fisica;
import org.mindrot.jbcrypt.BCrypt;
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

    private void setNombre(String nombreUsuario) {
        this.nombre = nombreUsuario;
    }

    private void setContrasenia(String contrasena) {
        this.contrasenia = contrasena;
    }


    public String getContrasenia() {
        return contrasenia;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }
}
