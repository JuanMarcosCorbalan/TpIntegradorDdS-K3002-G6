package org.example.Formas_contribucion;

import java.util.ArrayList;
import java.util.List;
import org.example.PersonaVulnerable.Tarjeta;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.PersonaVulnerable.TarjetaDuenio;


public class RegistrarPersonasSV extends Contribucion{
    List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
    List<PersonaSituacionVulnerable> personasSituacionVulnerable = new ArrayList<PersonaSituacionVulnerable>();

    public void asignarTarjetas(){
        for(PersonaSituacionVulnerable personaSituacion : personasSituacionVulnerable){
            if(!tarjetas.isEmpty()) {
                Tarjeta tarjeta_asignar = tarjetas.remove(0);
                TarjetaDuenio nueva_tarjeta = new TarjetaDuenio(tarjeta_asignar, colaborador, personaSituacion);
                //ESTA BIEN LLAMADO A colaborador?
            }
        }
    }
    public int cantidadTarjetas(){
        return tarjetas.size();
    }
}
