package org.example.Heladeras;

public class HeladeraDTO2 {
    private long id;
    private String idHeladera;
    private String nombre;

    public HeladeraDTO2(long id, String idHeladera, String nombre) {
        this.id = id;
        this.idHeladera = idHeladera;
        this.nombre = nombre;
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

}
