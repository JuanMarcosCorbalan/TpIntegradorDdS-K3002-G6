package org.example.Formas_contribucion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.example.Colaborador.Colaborador;
import org.example.Persona.Domicilio;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Tarjetas.TarjetaSv;


public class RegistrarPersonasSV extends Contribucion{
    //List<Tarjeta> tarjetas = new ArrayList<Tarjeta>(); podria utilizar repo de tarjetas que ya tiene el sistema y q dio de alta antes
    List<String> ids_tarjetas = new LinkedList<String>();
    List<PersonaSituacionVulnerable> personasSituacionVulnerable = new LinkedList<PersonaSituacionVulnerable>();
    Integer cantidadTarjetasRepartidas;
    Integer registrosPendientes;

    public void asignarTarjetas(Colaborador colaborador){    //PRUEBA DE LOS PUNTOS SUGERIDOS
        int contador = 0;
        for(PersonaSituacionVulnerable personaSituacion : personasSituacionVulnerable){
            String id_tarjeta = ids_tarjetas.remove(contador);
            TarjetaSv nueva_tarjetaSv = new TarjetaSv(id_tarjeta,colaborador,personaSituacion);
            personaSituacion.setTarjetaSv(nueva_tarjetaSv);
            contador++;
        }
    }

    public void asignarTarjeta(Colaborador colaborador) {
        if (registrosPendientes > 0) {
            String id_tarjeta = ids_tarjetas.remove(0);
            PersonaSituacionVulnerable personaSituacion = personasSituacionVulnerable.remove(0);
            TarjetaSv nueva_tarjetaSv = new TarjetaSv(id_tarjeta, colaborador, personaSituacion);
            personaSituacion.setTarjetaSv(nueva_tarjetaSv);
            registrosPendientes--;
        }else {
            throw new IllegalArgumentException("NO QUEDAN REGISTROS PENDIENTES");
        }
    }
    /* public void PersonaSituacionVulnerable(String nombre, String apellido, Boolean enSituacionCalle, Domicilio domicilio, Boolean poseeMenoresACargo, Integer cantidadMenoresACargo, TarjetaSv tarjetaSv) {
        this.fechaRegistroSistema = new Date();
        this.enSituacionCalle = enSituacionCalle;
        //this.domicilio = domicilio;
        this.poseeMenoresACargo = poseeMenoresACargo;
        this.cantidadMenoresACargo = cantidadMenoresACargo;
        this.tarjetaSv = tarjetaSv;
        this.persona = new Persona_fisica(nombre,apellido,null,null,null,domicilio);
    }*/


    public void cargarDatosPersonasSv(String nombre,String apellido, boolean situacionCalle, String domicilioString, Integer menoresACargo) {
        for (int i = 0; i < this.cantidadTarjetas(); i++) {

            Domicilio domicilio = new Domicilio();
            domicilio.setDireccion(domicilioString);

            // Creamos la nueva persona con los datos solicitados
            PersonaSituacionVulnerable personaSituacionVulnerableNueva = new PersonaSituacionVulnerable(
                    nombre,
                    apellido,
                    situacionCalle,
                    domicilio,
                    menoresACargo);

            // Añadimos la persona a la lista
            personasSituacionVulnerable.add(personaSituacionVulnerableNueva);
        }
    }

    public void cargarDatosPersonaSv(String nombre,String apellido, boolean situacionCalle, String domicilioString, Integer menoresACargo){
        if (registrosPendientes > 0) {
            Domicilio domicilio = new Domicilio();
            domicilio.setDireccion(domicilioString);

            // Creamos la nueva persona con los datos solicitados
            PersonaSituacionVulnerable personaSituacionVulnerableNueva = new PersonaSituacionVulnerable(
                    nombre,
                    apellido,
                    situacionCalle,
                    domicilio,
                    menoresACargo);

            // Añadimos la persona a la lista
            personasSituacionVulnerable.add(personaSituacionVulnerableNueva);
        } else {
            throw new IllegalArgumentException("NO QUEDAN REGISTROS PENDIENTES");
        }
    }

    public int cantidadTarjetas(){
        return ids_tarjetas.size();
    }

    public RegistrarPersonasSV(Integer cantidadTarjetasRepartidas, LocalDate fechaColaboracion) {
        super(fechaColaboracion);
        this.cantidadTarjetasRepartidas = cantidadTarjetasRepartidas;
        this.registrosPendientes = cantidadTarjetasRepartidas;
    }

    @Override
    public double calcular_puntos() {
        return cantidadTarjetasRepartidas*ConstCalculo.TARJETAS_REPARTIDAS.getValor();
    }

    public String solicitarNombre(){
        return System.console().readLine();
    }
    public String solicitarApellido(){
        return System.console().readLine();
    }
    public Boolean solicitarSituacionCalle(){
        return (System.console().readLine() == "1");
    }

    public String solicitarDomicilio(){
        return System.console().readLine();
    }

    public Integer solicitarMenoresACargo(){
        return Integer.parseInt(System.console().readLine());
    }


}
