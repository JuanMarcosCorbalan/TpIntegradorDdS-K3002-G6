package org.example.Ofertas;

import java.io.File;

public class Oferta {
    String nombre;
    Integer puntosNecesarios;
    File imagenIlustrativa;


    // tendria que eliminarse de algun lado o son reutilizables las ofertas?
    public void canjear(){

    }

    // GETTERS AND SETTERS


    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
