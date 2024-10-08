package org.example.Ofertas;

import org.example.Persona.Ofertante;

public class OfrecerOfertaOfertante {
    Oferta ofertaAOfrecer;
    OfertasRubro ofertasRubro;
    Ofertante ofertante;

    public void aniadirOferta(){
        ofertasRubro.aniadirOferta(ofertaAOfrecer);
    }
}
