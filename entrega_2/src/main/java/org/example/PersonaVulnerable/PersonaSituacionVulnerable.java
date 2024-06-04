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


    public void PersonaSituacionVulnerable(Boolean enSituacionCalle,Domicilio domicilio,Boolean poseeMenoresACargo,Integer cantidadMenoresACargo,TarjetaDuenio tarjeta) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        this.domicilio = domicilio;
        this.poseeMenoresACargo = poseeMenoresACargo;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.tarjeta = tarjeta;
    }
    public void retirarVianda(Date fechaActual, Time horaActual, Heladera heladeraElegida){
        RetiroVianda nuevoRetiro = new RetiroVianda(this, fechaActual, horaActual, heladeraElegida);
        RetirarVianda retirarVianda = new RetirarVianda(heladeraElegida, nuevoRetiro, tarjeta);
        retirarVianda.retirarVianda();
    }

}
