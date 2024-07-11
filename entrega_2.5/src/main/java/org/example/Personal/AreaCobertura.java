package org.example.Personal;

public class AreaCobertura {
    String latitud;
    String longitud;
    String radio;

    public AreaCobertura(String latitud, String longitud, String radio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public void setRadio(String radio) {
        this.radio = radio;
    }
}
