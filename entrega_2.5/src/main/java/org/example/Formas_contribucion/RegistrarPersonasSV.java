package org.example.Formas_contribucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.Colaborador.Colaborador;
import org.example.Tarjetas.PersonaVulnerable.Tarjeta;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Tarjetas.Tarjeta;


public class RegistrarPersonasSV extends Contribucion{
    //List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
    List<String> ids_tarjetas = new ArrayList<String>();
    List<PersonaSituacionVulnerable> personasSituacionVulnerable = new ArrayList<PersonaSituacionVulnerable>();
    Integer cantidadTarjetasRepartidas;

    public void asignarTarjetas(Colaborador colaborador){    //PRUEBA DE LOS PUNTOS SUGERIDOS
        int contador = 0;
        for(PersonaSituacionVulnerable personaSituacion : personasSituacionVulnerable){
            String id_tarjeta = ids_tarjetas.remove(contador);
            Tarjeta nueva_tarjeta = new Tarjeta(id_tarjeta,colaborador,personaSituacion);
            contador++;
        }
    }

    public int cantidadTarjetas(){
        return ids_tarjetas.size();
    }

    public RegistrarPersonasSV(Integer cantidadTarjetasRepartidas, Date fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidadTarjetasRepartidas = cantidadTarjetasRepartidas;
    }

    @Override
    public double calcular_puntos() {
        return cantidadTarjetasRepartidas*ConstCalculo.TARJETAS_REPARTIDAS.getValor();
    }
}
