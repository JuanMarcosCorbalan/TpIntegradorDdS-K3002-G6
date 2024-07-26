package org.example.Suscripcion;

public class CreacionSuscripcion {

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo, Integer cantViandas){
        if(tipo.toString() == "QUEDANNVIANDAS") return new QuedanNViandas(cantViandas);
        else if (tipo.toString() == "FALTANNVIANDAS") return new FaltanNViandas(cantViandas);
        else if (tipo.toString() == "AVISOPORDESPERFECTO") return new AvisoPorDesperfecto();
        else return null;
    }
}
