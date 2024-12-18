package org.example.Personal;

import org.example.Heladeras.PuntoUbicacion;
import org.example.Persona.Persona_fisica;
import org.example.Validadores_Sensores.FallaTecnica;

public class AsignacionTecnico {

    public AsignacionTecnico() {}

    public void asginarTecnico(FallaTecnica fallaTecnica, PuntoUbicacion ubicacion) {
        Tecnico tecnico = buscarTecnico(ubicacion);
    }

    public Tecnico buscarTecnico(PuntoUbicacion ubicacion) {
        Tecnico tecnicoMasCercano = null;
        double menorDistancia = Double.MAX_VALUE;

        Tecnico[] tecnicos = new Tecnico[0];
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

        double latitud_tecnico = Double.parseDouble(tecnico.getAreaCobertura().getLatitud());
        double latitud_ubicacion = Double.parseDouble(ubicacion.getLatitud());
        double longitud_tecnico = Double.parseDouble(tecnico.getAreaCobertura().getLongitud());
        double longitud_ubicacion = Double.parseDouble(ubicacion.getLongitud());


        double latDiff = latitud_tecnico - latitud_ubicacion;
        double longDiff = longitud_tecnico - longitud_ubicacion;
        return Math.sqrt(latDiff * latDiff + longDiff * longDiff);
    }

}
