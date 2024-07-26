package org.example.Suscripcion;

public class AvisoPorDesperfecto extends Suscripcion {

    @Override
    public void darAviso(){
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.AVISOPORDESPERFECTO,"Falla en heladera");
        //enviarMensaje
    }
}
