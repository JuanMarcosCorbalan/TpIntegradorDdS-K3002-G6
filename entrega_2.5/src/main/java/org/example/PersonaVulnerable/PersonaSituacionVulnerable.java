package org.example.PersonaVulnerable;

import org.example.Persona.Domicilio;
import org.example.Heladeras.Heladera;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Rol;
import org.example.Tarjetas.TarjetaSv;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class PersonaSituacionVulnerable extends Rol {
    Date fechaRegistroSistema;
    Boolean enSituacionCalle;
    //Domicilio domicilio;
    //Boolean poseeMenoresACargo;
    Integer cantidadMenoresACargo;
    TarjetaSv tarjetaSv;


    public PersonaSituacionVulnerable(String nombre, String apellido, Boolean enSituacionCalle, Domicilio domicilio, Integer cantidadMenoresACargo, TarjetaSv tarjetaSv) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        //this.poseeMenoresACargo = poseeMenoresACargo;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.tarjetaSv = tarjetaSv;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }

    public PersonaSituacionVulnerable(String nombre, String apellido, Boolean enSituacionCalle, Domicilio domicilio, Integer cantidadMenoresACargo) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }

    public void retirarVianda(LocalDate fechaActual, LocalTime horaActual, Heladera heladeraElegida){
        /*RetiroVianda nuevoRetiro = new RetiroVianda(this, fechaActual, horaActual, heladeraElegida);
        RetirarVianda retirarVianda = new RetirarVianda(heladeraElegida, nuevoRetiro, tarjeta);
        retirarVianda.retirarVianda();
        heladeraElegida.aniadir_retiro(nuevoRetiro); //NUEVO ENTREGA 2, TRAZABILIDAD
         */
    }

    public Integer getCantidadMenoresACargo() {
        return cantidadMenoresACargo;
    }

    public void setTarjetaSv(TarjetaSv tarjetaSv) {
        this.tarjetaSv = tarjetaSv;
    }
}
