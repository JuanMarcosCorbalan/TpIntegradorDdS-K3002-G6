package org.example.Ofertas;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Contribucion;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

import static org.example.Formas_contribucion.EstadoContribucion.EXITOSA;

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
    String imagenIlustrativa;
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


    public Oferta(String nombre, Integer puntosNecesarios, Integer cantInstancias, Colaborador colaborador, String foto) {
        this.nombre = nombre;
        this.puntosNecesarios = puntosNecesarios;
        this.cant_instancias = cantInstancias;
        this.colaborador = colaborador;
        this.fecha_contribucion = LocalDate.now();
        this.imagenIlustrativa = foto;
        this.estado = EXITOSA;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public Integer getCant_instancias(){return cant_instancias;}

    public String getImagenIlustrativa(){return imagenIlustrativa;}

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
