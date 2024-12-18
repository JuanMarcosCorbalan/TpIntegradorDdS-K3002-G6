package org.example.Ofertas;

import org.example.Formas_contribucion.Rubro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OfertasRubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany(mappedBy = "ofertaRubro")
    @Transient
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
