package org.example.Suscripcion;


import org.example.Heladeras.Heladera;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class AdministradorSuscripciones {



    List<Suscripcion> suscripciones = new ArrayList<Suscripcion>();





    public AdministradorSuscripciones(List<Suscripcion> suscripciones){this.suscripciones=suscripciones;}
    //SUSCRIPCIONES -> Busco las que sean del tipo de suscripcion especifico y que en cantidad de viandas tengan eso

    public void notificarViandasFaltantes(Integer cantidadViandas)
    {
        //ITERO SOBRE LAS CLAVES, BUSCANDO LOS EVENTOS IGUALES
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof FaltanNViandas suscripcionFaltante)
            {
                if(suscripcionFaltante.getCantViandas().equals(cantidadViandas))
                {
                    suscripcionFaltante.darAviso();
                }
            }
        }

    }

    public void notificarViandasSobrantes(Integer cantidadViandas)
    {
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof QuedanNViandas suscripcionSobrante)
            {
                if(suscripcionSobrante.getCantViandas().equals(cantidadViandas))
                {
                    suscripcionSobrante.darAviso();
                }
            }
        }
    }

    public void notificarDesperfecto()
    {
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof AvisoPorDesperfecto suscripcionDesperfecto)
            {
                suscripcionDesperfecto.darAviso();
            }
        }
    }





}
