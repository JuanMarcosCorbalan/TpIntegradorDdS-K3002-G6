package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;

import javax.persistence.*;

@Entity
public class MensajeAviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerado e incremental
    private Long id;

    @Enumerated(EnumType.STRING)
    TipoSuscripcion tipoAviso;

    String descripcionMensaje;

    @ManyToOne
    Colaborador colaborador;


    public MensajeAviso(TipoSuscripcion tipoAviso, String descripcionMensaje) {
        this.tipoAviso = tipoAviso;
        this.descripcionMensaje = descripcionMensaje;
    }

    public MensajeAviso() {

    }

    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }
}
