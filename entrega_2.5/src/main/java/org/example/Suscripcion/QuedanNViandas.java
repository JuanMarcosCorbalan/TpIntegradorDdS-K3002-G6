package org.example.Suscripcion;

public class QuedanNViandas implements Suscripcion{
    static Integer cantViandas;

    public QuedanNViandas(Integer cantViandas) {
        QuedanNViandas.cantViandas = cantViandas;
    }

    public Integer getCantViandas() {
        return cantViandas;
    }

    @Override
    public void darAviso(){
        MensajeAviso mensaje = new MensajeAviso(TipoSuscripcion.QUEDANNVIANDAS,"Quedan menos de " + Integer.toString(cantViandas));
        //enviarMensaje
    }
}
