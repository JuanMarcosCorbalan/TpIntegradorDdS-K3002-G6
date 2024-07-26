package org.example.Suscripcion;


import java.util.HashMap;


public class AdministradorSuscripciones {
    HashMap<String, Suscripcion> suscriptores = new HashMap<>();

    public void suscribirse(String tipo_evento, Suscripcion suscripcion) {
        suscriptores.put(tipo_evento, suscripcion);
    }


    public void desuscribirse(String tipo_evento, Suscripcion suscripcion) {
        suscriptores.remove(tipo_evento, suscripcion);
    }

    public void notificar(String tipo_evento)
    {
        //ITERO SOBRE LAS CLAVES, BUSCANDO LOS EVENTOS IGUALES
        for(String clave : suscriptores.keySet())
        {
            if(clave.equals(tipo_evento))
            {
                Suscripcion suscripcion = suscriptores.get(clave);
                suscripcion.darAviso();
            }
        }
    }



}
