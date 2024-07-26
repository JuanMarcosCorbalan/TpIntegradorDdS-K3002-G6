package org.example.Suscripcion;

public class QuedanNViandas extends Suscripcion{
    static Integer cantViandas;

    public QuedanNViandas(Integer cantViandas) {
        this.cantViandas = cantViandas;
    }

    public static Integer getCantViandas() {
        return cantViandas;
    }

    @Override
    public void darAviso(){
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.QUEDANNVIANDAS,"Quedan menos de " + Integer.toString(cantViandas));
        //enviarMensaje
    }
}
