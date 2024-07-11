package org.example.Ofertas;

import java.io.File;

public class Oferta {
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


    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
