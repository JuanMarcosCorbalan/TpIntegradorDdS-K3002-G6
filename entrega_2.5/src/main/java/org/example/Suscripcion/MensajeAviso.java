package org.example.Suscripcion;

public class MensajeAviso {
    TipoSuscripcion tipoAviso;
    String descripcionMensaje;

    public MensajeAviso(TipoSuscripcion tipoAviso, String descripcionMensaje) {
        this.tipoAviso = tipoAviso;
        this.descripcionMensaje = descripcionMensaje;
    }
}
