package org.example.Ofertas;

import org.example.Colaborador.Ofertante;

public class OfrecerOfertaOfertante {
    Oferta ofertaAOfrecer;
    OfertasRubro ofertasRubro;
    Ofertante ofertante;

    public void aniadirOferta(){
        ofertasRubro.aniadirOferta(ofertaAOfrecer);
    }
}
