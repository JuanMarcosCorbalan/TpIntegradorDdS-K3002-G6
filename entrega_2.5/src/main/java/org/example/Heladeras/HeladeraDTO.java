package org.example.Heladeras;


public class HeladeraDTO {
    private String latitud;
    private String longitud;
    private EstadoHeladera estado;

    public HeladeraDTO(String latitud, String longitud, EstadoHeladera estado) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    // Getters
    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public EstadoHeladera getEstado() {
        return estado;
    }

    // Setters
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setEstado(EstadoHeladera estado) {
        this.estado = estado;
    }
}

