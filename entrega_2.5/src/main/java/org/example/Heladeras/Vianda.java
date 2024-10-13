package org.example.Heladeras;

import java.time.LocalDate;
import java.util.Date;

public class Vianda {
    String nombreComida;
    LocalDate fechaCaducidad;
    String kiloCalorias;
    String peso;

    public Vianda(String nombreComida, LocalDate fechaCaducidad, String kiloCalorias, String peso) {
        this.nombreComida = nombreComida;
        this.fechaCaducidad = fechaCaducidad;
        this.kiloCalorias = kiloCalorias;
        this.peso = peso;
    }

    public Vianda(String nombreComida) {
        this.nombreComida = nombreComida;
    }
}
