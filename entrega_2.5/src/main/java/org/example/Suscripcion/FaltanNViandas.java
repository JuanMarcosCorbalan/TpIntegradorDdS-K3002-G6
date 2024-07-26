package org.example.Suscripcion;

public class FaltanNViandas extends Suscripcion{

    static Integer cantViandas;

    public FaltanNViandas(Integer cantViandas) {
        this.cantViandas = cantViandas;
    }

    public static Integer getCantViandas() {
        return cantViandas;
    }

    @Override
    public void darAviso(){
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.FALTANNVIANDAS,"Faltan mas de " + Integer.toString(cantViandas));
        //enviarMensaje
    }
}
