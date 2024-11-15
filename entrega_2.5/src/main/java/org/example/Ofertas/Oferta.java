package org.example.Ofertas;

import org.example.Formas_contribucion.Contribucion;

import java.io.File;

public class Oferta extends Contribucion {
    String nombre;
    Integer puntosNecesarios;
    File imagenIlustrativa;
    //CAMBIO RESPECTO A LA ENTREGA 2
    Integer cant_instancias;

    // tendria que eliminarse de algun lado o son reutilizables las ofertas?
    public void canjear(){
        if(cant_instancias >= 1)
        {
            cant_instancias--;
        }
    }

    // GETTERS AND SETTERS


    public Oferta(String nombre, Integer puntosNecesarios, Integer cantInstancias) {
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.cant_instancias = cantInstancias;
    }

    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
