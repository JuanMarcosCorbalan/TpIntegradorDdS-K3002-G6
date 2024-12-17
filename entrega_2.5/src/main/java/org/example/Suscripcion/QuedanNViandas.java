package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import javax.persistence.Entity;
import java.io.IOException;

@Entity
public class QuedanNViandas extends Suscripcion{
    Integer cantViandasRest;

    public QuedanNViandas(Integer cantViandas, Colaborador colaborador, Heladera heladera) {
        this.cantViandasRest = cantViandas;
        this.colaborador = colaborador;
        this.heladera= heladera;
    }

    public QuedanNViandas() {

    }

    public Integer getCantViandas() {
        return cantViandasRest;
    }

    @Override
    public void darAviso() {
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.QUEDANNVIANDAS,"Quedan menos de " + Integer.toString(cantViandasRest));
        colaborador.procesar_mensaje(mensaje);
    }
}
