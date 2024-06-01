package org.example.PersonaVulnerable;

import org.example.Heladeras.Heladera;

import java.sql.Time;
import java.util.Date;

public class RetiroVianda {
    PersonaSituacionVulnerable personaSV;
    Date fechaRetiro;
    Time horaRetiro;
    Heladera heladeraRetiro;


    public PersonaSituacionVulnerable getPersonaSV() {
        return personaSV;
    }

    public void setPersonaSV(PersonaSituacionVulnerable personaSV) {
        this.personaSV = personaSV;
    }

    // CONSTRUCTOR
    public RetiroVianda(PersonaSituacionVulnerable personaSV, Date fechaRetiro, Time horaRetiro, Heladera heladeraRetiro) {
        this.personaSV = personaSV;
        this.fechaRetiro = fechaRetiro;
        this.horaRetiro = horaRetiro;
        this.heladeraRetiro = heladeraRetiro;
    }
}
