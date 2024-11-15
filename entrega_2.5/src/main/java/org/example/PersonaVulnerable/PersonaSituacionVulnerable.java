package org.example.PersonaVulnerable;

import org.example.Formas_contribucion.RegistrarPersonasSV;
import org.example.Persona.Domicilio;
import org.example.Heladeras.Heladera;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Rol;
import org.example.Tarjetas.TarjetaSv;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Entity
public class PersonaSituacionVulnerable extends Rol {

    Date fechaRegistroSistema;
    Boolean enSituacionCalle;
    Boolean poseeMenoresACargo;
    Integer cantidadMenoresACargo;
    //TarjetaSv tarjetaSv;

    //Domicilio domicilio;

    @ManyToOne
    private RegistrarPersonasSV registro;

    public PersonaSituacionVulnerable(String nombre, String apellido, Boolean enSituacionCalle, Domicilio domicilio, Integer cantidadMenoresACargo, TarjetaSv tarjetaSv) {
        //super(domicilio);
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        //this.poseeMenoresACargo = poseeMenoresACargo;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        //this.tarjetaSv = tarjetaSv;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }

    public PersonaSituacionVulnerable(String nombre, String apellido, Boolean enSituacionCalle, Domicilio domicilio, Integer cantidadMenoresACargo) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }

    public PersonaSituacionVulnerable() {

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
    public void setEnSituacionCalle(Boolean enSituacionCalle) {
        this.enSituacionCalle = enSituacionCalle;
    }
    public void setPoseeMenoresACargo(Boolean poseeMenoresACargo) {
        this.poseeMenoresACargo = poseeMenoresACargo;
    }
    public void setCantidadMenoresACargo(Integer cantidadMenoresACargo) {
        this.cantidadMenoresACargo = cantidadMenoresACargo;
    }
   // public void setTarjetaSv(TarjetaSv tarjetaSv) {this.tarjetaSv = tarjetaSv;}
}
