package org.example.Ofertas;

import org.example.Formas_contribucion.Rubro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class OfertasRubro {



    //@OneToMany(mappedBy = "ofertaRubro")

    private List<Oferta> ofertas;


    Rubro rubro;

    //List<Oferta> ofertas = new ArrayList<Oferta>();

    public void aniadirOferta(Oferta unaOferta){
        ofertas.add(unaOferta);
    }

    public void removerOferta(Oferta unaOferta){
        ofertas.remove(unaOferta);
    }
}
