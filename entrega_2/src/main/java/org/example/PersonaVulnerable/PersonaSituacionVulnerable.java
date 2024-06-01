package org.example.PersonaVulnerable;

import org.example.Colaborador.Domicilio;
import org.example.Heladeras.Heladera;

import java.sql.Time;
import java.util.Date;

public class PersonaSituacionVulnerable {
    Date fechaRegistroSistema;
    Boolean enSituacionCalle;
    Domicilio domicilio;
    Boolean poseeMenoresACargo;
    Integer cantidadMenoresACargo;
    TarjetaDuenio tarjeta;


    public void retirarVianda(Date fechaActual, Time horaActual, Heladera heladeraElegida){
        RetiroVianda nuevoRetiro = new RetiroVianda(this, fechaActual, horaActual, heladeraElegida);
        RetirarVianda retirarVianda = new RetirarVianda(heladeraElegida, nuevoRetiro, tarjeta);
        retirarVianda.retirarVianda();
    }
}
