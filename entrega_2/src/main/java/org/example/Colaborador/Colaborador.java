package org.example.Colaborador;

import org.example.Formas_contribucion.*;
import org.example.Heladeras.Heladera;
import org.example.Ofertas.Oferta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colaborador {

    Persona persona_colaboradora;
    List<Contribucion> contribuciones = new ArrayList<Contribucion>();
    Forma_colaborar[] formas_de_colaborar;
    //Heladera heladeras_a_cargo[];
    List<Heladera> heladeras_a_cargo;
    int puntos;

    public void aniadirMedioContacto(){

    };
    public void notificarPorMedios(){

    };

    // si se pasa un parametro realiza esa contribucion
    // habria que validar que el colaborador pueda hacerlo
    public void realizar_contribucion(Contribucion contribucion_a_realizar) {
        if(contribucion_a_realizar.verificar_colaborador(this))
        {
            contribucion_a_realizar.realizar_contribucion();
            contribuciones.add(contribucion_a_realizar);
        }
    }
    public void agregarContribucion(Contribucion nuevaContribucion){
        contribuciones.add(nuevaContribucion);
    }
    // si no se pasa un parametro realiza la primera que haya en la lista
    /*
    public void realizar_contribucion(){
        Contribucion contribucion_a_realizar = contribuciones.removeFirst();
        contribucion_a_realizar.realizar_contribucion();
    }*/

    public Colaborador(Persona persona,Forma_colaborar formas[])
    {
        this.persona_colaboradora = persona;
        this.contribuciones = new ArrayList<Contribucion>();
        this.formas_de_colaborar = formas;
        this.heladeras_a_cargo = new ArrayList<Heladera>();
    }

    public Colaborador(Persona persona_colaboradora) {
        this.persona_colaboradora = persona_colaboradora;
    }

    public int getPuntos() {
        int pesos_donados = 0;
        int viandas_distribuidas = 0;
        int viandas_donadas = 0;
        int tarjetas_repartidas = 0;
        for (Contribucion contribucion : contribuciones)
        {
            if(contribucion instanceof Donacion_dinero)
            {
                pesos_donados += ((Donacion_dinero) contribucion).getMonto();
            }
            else if(contribucion instanceof Distribucion_viandas)
            {
                viandas_distribuidas += ((Distribucion_viandas) contribucion).getCantidad_viandas_a_mover();
            }
            else if(contribucion instanceof Donacion_viandas)
            {
                viandas_donadas += ((Donacion_viandas) contribucion).cant_viandas();
            }
            else
            {
                tarjetas_repartidas += ((RegistrarPersonasSV) contribucion).cantidadTarjetas();
            }
        }
        puntos = (int) (CoeficientesCalculoPuntos.pesos_donados * pesos_donados + CoeficientesCalculoPuntos.viandas_distribuidas * viandas_distribuidas
                + CoeficientesCalculoPuntos.viandas_donadas * viandas_donadas + CoeficientesCalculoPuntos.tarjetas_repartidas * tarjetas_repartidas);
        return puntos;
    }

    public void canjearOferta(Oferta oferta)
    {
        if(oferta.getPuntosNecesarios()<=puntos)
        {
            oferta.canjear();
        }
        //QUE HAGA ALGO SI NO PUEDE CANJEAR
    }


    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Persona getPersona_colaboradora() {
        return persona_colaboradora;
    }

    public List<Contribucion> getContribuciones() {
        return contribuciones;
    }

    public void setContribuciones(List<Contribucion> contribuciones) {
        this.contribuciones = contribuciones;
    }
}

