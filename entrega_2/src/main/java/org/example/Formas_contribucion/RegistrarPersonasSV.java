package org.example.Formas_contribucion;

import java.util.ArrayList;
import java.util.List;
import org.example.PersonaVulnerable.Tarjeta;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;


public class RegistrarPersonasSV {
    List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
    List<PersonaSituacionVulnerable> personasSituacionVulnerable = new ArrayList<PersonaSituacionVulnerable>();

    public void asignarTarjetas(){
        // le asigna a cada persona de la lista de personas en situacion vulnerable una tarjeta
    }
    public int cantidadTarjetas(){
        return tarjetas.size();
    }
}
