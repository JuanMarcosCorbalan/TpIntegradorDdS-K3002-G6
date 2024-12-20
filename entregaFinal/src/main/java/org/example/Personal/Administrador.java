package org.example.Personal;

import org.example.Heladeras.Heladera;
import org.example.Persona.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Administrador extends Rol {

    String nombre;

    public Administrador() {
    }

    public Administrador(Persona_fisica personaFisica){
        this.persona = personaFisica;

    }

    public void darDeAltaHeladera(Heladera heladera){
        heladera.activar();
    }

    public void darDeBajaHeladera(Heladera heladera){
        heladera.desactivar();
    }
}
