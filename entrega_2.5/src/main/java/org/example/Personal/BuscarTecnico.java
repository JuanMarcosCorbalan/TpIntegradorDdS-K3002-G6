package org.example.Personal;

import org.example.Heladeras.PuntoUbicacion;
import org.example.Persona.Persona_fisica;

import java.util.List;

public class BuscarTecnico {
    public Tecnico buscarTecnico(List<Tecnico> tecnicos, PuntoUbicacion ubicacion) {
        Tecnico tecnicoMasCercano = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Tecnico tecnico : tecnicos) {
            double distancia = this.distanciaA(ubicacion, tecnico);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                tecnicoMasCercano = tecnico;
            }
        }


        if (tecnicoMasCercano != null) {
            Persona_fisica pf = (Persona_fisica) tecnicoMasCercano.getPersona();
            System.out.println("El técnico más cercano es: " + pf.getNombre());
        } else {
            System.out.println("No se encontró ningún técnico.");
        }

        return tecnicoMasCercano;
    }

    public double distanciaA(PuntoUbicacion ubicacion,Tecnico tecnico) {
        double latDiff = tecnico.getAreaCobertura().getLatitud() - ubicacion.getLatitud();
        double longDiff = tecnico.getAreaCobertura().getLongitud()- ubicacion.getLongitud();
        return Math.sqrt(latDiff * latDiff + longDiff * longDiff);
    }
}
