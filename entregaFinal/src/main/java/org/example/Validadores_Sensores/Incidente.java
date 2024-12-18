package org.example.Validadores_Sensores;

import jdk.jfr.Enabled;
import org.example.Heladeras.Heladera;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    Heladera heladera;

    LocalDate fecha;
    LocalTime hora;

    @Enumerated(EnumType.STRING)
    TipoIncidente tipoIncidente;

    public Incidente(Heladera heladera, TipoIncidente tipoIncidente) {
        this.heladera = heladera;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = tipoIncidente;
    }

    public Incidente() {
    }

    public Heladera getHeladera() {
        return heladera;
    }

    public LocalDate getFecha() { return fecha;
    }

    public LocalTime getHora() {return hora;
    }

    public Long getId(){return id;}
}
