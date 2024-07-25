package org.example.Colaborador;

import org.example.Formas_contribucion.*;
import org.example.Heladeras.Heladera;
import org.example.Ofertas.Oferta;
import org.example.Persona.CoeficientesCalculoPuntos;
import org.example.Persona.Persona;
import org.example.Persona.Rol;
import org.example.Validadores_Sensores.FallaTecnica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Colaborador extends Rol {

    List<Contribucion> contribuciones = new ArrayList<Contribucion>();
    Forma_colaborar[] formas_de_colaborar;
    //Heladera heladeras_a_cargo[];
    List<Heladera> heladeras_a_cargo;
    //double puntos;

    public void aniadirMedioContacto(){

    };
    public void notificarPorMedios(){

    };

    // si se pasa un parametro realiza esa contribucion
    // habria que validar que el colaborador pueda hacerlo

    /* CAMBIAR LA LOGICA DE REALIZAR CONTRIBUCION CON LO QUE NOS DIJERON EN LA ENTREGA 2.5
    public void realizar_contribucion(Contribucion contribucion_a_realizar) {
        if(contribucion_a_realizar.verificar_colaborador(this))
        {
            contribucion_a_realizar.realizar_contribucion();
            contribuciones.add(contribucion_a_realizar);
        }
    }

    */

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
        this.persona = persona;
        this.contribuciones = new ArrayList<Contribucion>();
        this.formas_de_colaborar = formas;
        this.heladeras_a_cargo = new ArrayList<Heladera>();
    }

    public Colaborador(Persona persona_colaboradora) {
        this.persona = persona_colaboradora;
    }

    public double getPuntos() {
        double puntos = 0;
        
        for (Contribucion contribucion : contribuciones)
        {
            puntos+= contribucion.calcular_puntos();
        }
        
        return puntos;
    }

    public void canjearOferta(Oferta oferta)
    {
        if(oferta.getPuntosNecesarios()<=getPuntos())
        {
            oferta.canjear();
        }
        //QUE HAGA ALGO SI NO PUEDE CANJEAR
    }


    /*public void setPuntos(int puntos) {
        this.puntos = puntos;
    }*/

    public Persona getPersona_colaboradora() {
        return persona;
    }

    public List<Contribucion> getContribuciones() {
        return contribuciones;
    }

    public void setContribuciones(List<Contribucion> contribuciones) {
        this.contribuciones = contribuciones;
    }

    public void reportarFallaTenica(Heladera heladera, String descripcion, File foto)
    {
        heladera.desactivar();
        FallaTecnica fallaTecnica = this.reportarIncidente(this,descripcion,foto,heladera);
        fallaTecnica.asignarTecnico(heladera.getPuntoUbicacion());
    }
    public FallaTecnica reportarIncidente(Colaborador colaborador,String descripcion,File foto,Heladera heladera)
    {
        return new FallaTecnica(colaborador,descripcion,foto,heladera);
    }
}

