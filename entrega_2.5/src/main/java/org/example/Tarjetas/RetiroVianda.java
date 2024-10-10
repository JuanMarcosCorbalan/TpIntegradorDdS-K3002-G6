package org.example.Tarjetas;

import org.example.Heladeras.Heladera;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroVianda {
    PersonaSituacionVulnerable personaSV;
    LocalDate fechaRetiro;
    LocalTime horaRetiro;
    Heladera heladeraRetiro;


    public PersonaSituacionVulnerable getPersonaSV() {
        return personaSV;
    }

    public void setPersonaSV(PersonaSituacionVulnerable personaSV) {
        this.personaSV = personaSV;
    }

    // CONSTRUCTOR
    public RetiroVianda(PersonaSituacionVulnerable personaSV, LocalDate fechaRetiro, LocalTime horaRetiro, Heladera heladeraRetiro) {
        this.personaSV = personaSV;
        this.fechaRetiro = fechaRetiro;
        this.horaRetiro = horaRetiro;
        this.heladeraRetiro = heladeraRetiro;
    }
}
