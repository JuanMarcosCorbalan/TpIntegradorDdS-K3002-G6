package org.example.Heladeras;

import java.util.Date;

public class Vianda {
    String nombreComida;
    Date fechaCaducidad;
    String kiloCalorias;
    Float peso;

    public Vianda(String nombreComida, Date fechaCaducidad, String kiloCalorias, Float peso) {
        this.nombreComida = nombreComida;
        this.fechaCaducidad = fechaCaducidad;
        this.kiloCalorias = kiloCalorias;
        this.peso = peso;
    }

    public Vianda(String nombreComida) {
        this.nombreComida = nombreComida;
    }
}
