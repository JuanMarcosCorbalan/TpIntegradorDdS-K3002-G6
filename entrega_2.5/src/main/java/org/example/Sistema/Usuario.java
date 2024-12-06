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

    String contraseniaHash;



    public Usuario() {}

    public Usuario(Persona persona, String nombre, String contrasenia) {
        this.persona = persona;
        this.nombre = nombre;
        this.contraseniaHash = contrasenia;
    }

    private void setNombre(String nombreUsuario) {
        this.nombre = nombreUsuario;
    }

    private void setContrasenaHash(String contrasenaHash) {
        this.contraseniaHash = contrasenaHash;
    }


    public String getContraseniaHash() {
        return contraseniaHash;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
