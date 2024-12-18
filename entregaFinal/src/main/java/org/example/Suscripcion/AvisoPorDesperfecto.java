package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import javax.persistence.Entity;
import java.io.IOException;

@Entity
public class AvisoPorDesperfecto extends Suscripcion {


    public AvisoPorDesperfecto(Colaborador colaborador, Heladera heladera) {
        this.colaborador = colaborador;
        this.heladera = heladera;
    }

    public AvisoPorDesperfecto() {

    }

    @Override
    public void darAviso() {
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.AVISOPORDESPERFECTO,"Falla en heladera");
        colaborador.procesar_mensaje(mensaje);
    }
}
