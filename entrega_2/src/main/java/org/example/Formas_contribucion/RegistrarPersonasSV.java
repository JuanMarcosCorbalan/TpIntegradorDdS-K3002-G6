package org.example.Formas_contribucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.example.PersonaVulnerable.Tarjeta;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;


public class RegistrarPersonasSV extends Contribucion {
    List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
    List<PersonaSituacionVulnerable> personasSituacionVulnerable = new ArrayList<PersonaSituacionVulnerable>();
    Integer cantidadTarjetasRepartidas =  this.cantidadTarjetas();


    public void asignarTarjetas(){
        // le asigna a cada persona de la lista de personas en situacion vulnerable una tarjeta
    }
    public int cantidadTarjetas(){
        return tarjetas.size();
    }

    public RegistrarPersonasSV(Integer cantidadTarjetasRepartidas, Date fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidadTarjetasRepartidas = cantidadTarjetasRepartidas;
    }
}
