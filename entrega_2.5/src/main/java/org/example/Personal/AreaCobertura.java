package org.example.Personal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AreaCobertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerado e incremental
    private Long id;

    String latitud;
    String longitud;
    String radio;

    public AreaCobertura(String latitud, String longitud, String radio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }

    public AreaCobertura() {

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

    public String getLatitud(){ return latitud;}
    public String getLongitud(){ return longitud;}


}
