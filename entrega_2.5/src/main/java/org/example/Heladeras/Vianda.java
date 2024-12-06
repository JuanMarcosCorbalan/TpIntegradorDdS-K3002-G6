package org.example.Heladeras;

import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.RegistrarPersonasSV;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Vianda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombreComida;
    LocalDate fechaCaducidad;
    String kiloCalorias;
    String peso;

    @ManyToOne
    private Distribucion_viandas registro;

    @ManyToOne
    private Heladera heladera;


    public Vianda(String nombreComida, LocalDate fechaCaducidad, String kiloCalorias, String peso) {
        this.nombreComida = nombreComida;
        this.fechaCaducidad = fechaCaducidad;
        this.kiloCalorias = kiloCalorias;
        this.peso = peso;
    }

    public Vianda(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public Vianda() {

    }
}
