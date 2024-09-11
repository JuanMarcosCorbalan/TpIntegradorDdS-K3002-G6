package org.example.Personal;

public class AreaCobertura {
    Integer latitud;
    Integer longitud;
    String radio;

    public AreaCobertura(Integer latitud, Integer longitud, String radio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }
    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }
    public void setRadio(String radio) {
        this.radio = radio;
    }

    public Integer getLatitud(){ return latitud;}
    public Integer getLongitud(){ return longitud;}


}
