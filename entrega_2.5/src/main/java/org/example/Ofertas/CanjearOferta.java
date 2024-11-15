package org.example.Ofertas;

import org.example.Colaborador.Colaborador;

public class CanjearOferta {
    Oferta ofertaSolicitada;
    Colaborador colaborador;

    public boolean verificarPuntos(){
        return colaborador.obtenerPuntos() > ofertaSolicitada.getPuntosNecesarios();
    }

    public void otorgarBeneficio(){
        if(!this.verificarPuntos()){
            throw new Error("puntos insuficientes!"); // algo asi ponele
        }
    }
}
