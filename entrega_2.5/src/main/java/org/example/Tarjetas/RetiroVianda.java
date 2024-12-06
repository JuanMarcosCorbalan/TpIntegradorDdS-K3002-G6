package org.example.Tarjetas;

import org.example.Heladeras.Heladera;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class RetiroVianda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //PersonaSituacionVulnerable personaSV;
    LocalDate fechaRetiro;
    LocalTime horaRetiro;

    @ManyToOne
    @JoinColumn (name = "retiro_heladera")
    public Heladera heladeraRetiro;

    @ManyToOne
    @JoinColumn (name = "retiro_tarjeta")
    public TarjetaSv tarjetasv;


    //public PersonaSituacionVulnerable getPersonaSV() return personaSV;}

    //public void setPersonaSV(PersonaSituacionVulnerable personaSV) this.personaSV = personaSV;}

    // CONSTRUCTOR
    public RetiroVianda(LocalDate fechaRetiro, LocalTime horaRetiro, Heladera heladeraRetiro, TarjetaSv tarjetaSV) {
        this.fechaRetiro = fechaRetiro;
        this.horaRetiro = horaRetiro;
        this.heladeraRetiro = heladeraRetiro;
        this.tarjetasv = tarjetaSV;
    }

    public RetiroVianda() {

    }
}
