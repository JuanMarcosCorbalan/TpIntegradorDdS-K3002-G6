package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

public class CreacionSuscripcion {

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo, Integer cantViandas, Colaborador colaborador, Heladera heladera){
        if(tipo.toString() == "QUEDANNVIANDAS") return new QuedanNViandas(cantViandas,colaborador,heladera);
        else if (tipo.toString() == "FALTANNVIANDAS") return new FaltanNViandas(cantViandas,colaborador,heladera);
        else if (tipo.toString() == "AVISOPORDESPERFECTO") return new AvisoPorDesperfecto(colaborador,heladera);
        else return null;
    }


}
