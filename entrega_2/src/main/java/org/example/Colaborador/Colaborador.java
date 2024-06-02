package org.example.Colaborador;

import org.example.Heladeras.Heladera;
import org.example.Formas_contribucion.Contribucion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colaborador {

    Persona persona_colaboradora;
    Medio_contacto medios_de_contacto[];
    //List<Contribucion> contribuciones = new ArrayList<Contribucion>();
    List<Contribucion> contribuciones;
    Forma_colaborar formas_de_colaborar[];
    //Heladera heladeras_a_cargo[];
    List<Heladera> heladeras_a_cargo;
    int puntos;

    public void aniadirMedioContacto(){

    };
    public void notificarPorMedios(){

    };

    // si se pasa un parametro realiza esa contribucion
    // habria que validar que el colaborador pueda hacerlo
    public void realizar_contribucion(Contribucion Contribucion_a_realizar) {
        Contribucion_a_realizar.realizar_contribucion();
    }

    // si no se pasa un parametro realiza la primera que haya en la lista
    public void realizar_contribucion(){
        Contribucion contribucion_a_realizar = contribuciones.removeFirst();
        contribucion_a_realizar.realizar_contribucion();
    }
    //CONSTRUCTOR
    public Colaborador(Persona persona,Medio_contacto mediosContacto[],Forma_colaborar formas[])
    {
        this.persona_colaboradora = persona;
        this.medios_de_contacto = mediosContacto;
        this.contribuciones = new ArrayList<Contribucion>();
        this.formas_de_colaborar = formas;
        this.heladeras_a_cargo = new ArrayList<Heladera>();
    }


    // GETTERS AND SETTERS


    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
