package org.example.Heladeras;

public class HeladeraDTO2 {
    private long id;
    private String idHeladera;
    private String nombre;
    private int capacidad_maxima;
    private int cantidadViandasActual;

    public HeladeraDTO2(long id, String idHeladera, String nombre, int capacidad_maxima, int cantViandas) {
        this.id = id;
        this.idHeladera = idHeladera;
        this.nombre = nombre;
        this.capacidad_maxima = capacidad_maxima;
        this.cantidadViandasActual = cantViandas;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getIdHeladera() {
        return idHeladera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad_maxima() {return capacidad_maxima;}

}
