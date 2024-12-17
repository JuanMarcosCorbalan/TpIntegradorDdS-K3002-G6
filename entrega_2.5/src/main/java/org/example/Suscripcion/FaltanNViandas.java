package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import javax.persistence.Entity;
import java.io.IOException;

@Entity
public class FaltanNViandas extends Suscripcion{

    Integer cantViandasFalt;

    public FaltanNViandas(Integer cantViandas, Colaborador colaborador, Heladera heladera) {
        this.cantViandasFalt = cantViandas;
        this.colaborador = colaborador;
        this.heladera = heladera;
    }

    public FaltanNViandas() {

    }

    public  Integer getCantViandas() {
        return cantViandasFalt;
    }

    @Override
    public void darAviso()  {

        String mensaje = "La heladera a la que está suscripto " +heladera.getIdHeladera()+ " \uD83E\uDDCA le faltan "+ Integer.toString(cantViandasFalt) +
                " viandas \uD83C\uDF71 para llenarse!\n" +
                "Inicie sesión en la página para colaborar \uD83D\uDCBB hobbits.com.ar \uD83D\uDE4C";
        MensajeAviso mensajeAviso = new MensajeAviso(TipoSuscripcion.FALTANNVIANDAS,mensaje);
        colaborador.procesar_mensaje(mensajeAviso);
    }
}
