package org.example.Formas_contribucion;

import org.example.Ofertas.Oferta;
import org.example.Ofertas.OfertasRubro;

public class OfrecerOferta extends Contribucion{
    Rubro rubro;
    Oferta oferta;
    OfertasRubro ofertasRubro;

    public void aniadirOferta(){
        ofertasRubro.aniadirOferta(oferta);
    }
}
