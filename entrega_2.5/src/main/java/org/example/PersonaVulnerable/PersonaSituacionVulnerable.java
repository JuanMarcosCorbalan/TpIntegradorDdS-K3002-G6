package org.example.PersonaVulnerable;

import org.example.Persona.Domicilio;
import org.example.Heladeras.Heladera;
import org.example.Persona.Persona;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Rol;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class PersonaSituacionVulnerable extends Rol {
    Date fechaRegistroSistema;
    Boolean enSituacionCalle;
    //Domicilio domicilio;
    Boolean poseeMenoresACargo;
    Integer cantidadMenoresACargo;
    Tarjeta tarjeta;


    public void PersonaSituacionVulnerable(String nombre,String apellido,Boolean enSituacionCalle,Domicilio domicilio,Boolean poseeMenoresACargo,Integer cantidadMenoresACargo,Tarjeta tarjeta) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        this.poseeMenoresACargo = poseeMenoresACargo;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.tarjeta = tarjeta;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }

    public void retirarVianda(LocalDate fechaActual, LocalTime horaActual, Heladera heladeraElegida){
        RetiroVianda nuevoRetiro = new RetiroVianda(this, fechaActual, horaActual, heladeraElegida);
        RetirarVianda retirarVianda = new RetirarVianda(heladeraElegida, nuevoRetiro, tarjeta);
        retirarVianda.retirarVianda();
        heladeraElegida.aniadir_retiro(nuevoRetiro); //NUEVO ENTREGA 2, TRAZABILIDAD
    }

}
