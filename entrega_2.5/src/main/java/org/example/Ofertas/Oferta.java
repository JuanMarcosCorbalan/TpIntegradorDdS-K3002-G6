package org.example.Ofertas;

import org.example.Formas_contribucion.Contribucion;

import javax.persistence.*;
import java.io.File;

@Entity
public class Oferta extends Contribucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ofertaRubro")
    private OfertasRubro ofertaRubro;

    String nombre;
    Integer puntosNecesarios;
    File imagenIlustrativa;
    //CAMBIO RESPECTO A LA ENTREGA 2
    Integer cant_instancias;

    public Oferta() {

    }

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

    public String getNombre() {
        return nombre;
    }

    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public Integer getCant_instancias(){return cant_instancias;}

    public File getImagenIlustrativa(){return imagenIlustrativa;}

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
