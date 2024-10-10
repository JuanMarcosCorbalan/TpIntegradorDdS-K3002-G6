package org.example.Colaborador;

import org.example.Formas_contribucion.*;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;
import org.example.Ofertas.Oferta;
import org.example.Persona.Persona;
import org.example.Persona.Rol;
import org.example.Suscripcion.*;
import org.example.Personal.Tecnico;
import org.example.Tarjetas.SolicitudApertura;
import org.example.Tarjetas.SolicitudWeb;
import org.example.Tarjetas.TarjetaColaborador;
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
    Integer viandasDonadas = 0;
    TarjetaColaborador Tarjeta;


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
        List<Tecnico> tecnicos = new ArrayList<>();//IRREAL, DEBERIA TOMAR LA LISTA REAL CON LOS TECNICOS
        FallaTecnica fallaTecnica = this.reportarIncidente(this,descripcion,foto,heladera);
        fallaTecnica.asignarTecnico(heladera.getPuntoUbicacion(),tecnicos);
    }

    public FallaTecnica reportarIncidente(Colaborador colaborador,String descripcion,File foto,Heladera heladera)
    {
        return new FallaTecnica(colaborador,descripcion,foto,heladera);
    }

    public void suscribirseAHeladera(Heladera heladeraASuscribirse, TipoSuscripcion tipoSuscripcion, Integer numeroViandas){
        Suscripcion suscripcion = CreacionSuscripcion.crearSuscripcion(tipoSuscripcion, numeroViandas);
        heladeraASuscribirse.getAdmin_suscr().suscribirse(CreacionSuscripcion.definirEveneto(tipoSuscripcion,numeroViandas),suscripcion);
    }

    public Integer getCantidadViandasDonadas(){
        for(Contribucion contribucion: contribuciones){
            if(contribucion instanceof Donacion_viandas){
                viandasDonadas += 1;
            }
        }
        return viandasDonadas;
    }

    private void solicitarDonacionVianda(Heladera HeladeraAIngresarViandas, Vianda ViandaADonar){

        int flagHeladera = HeladeraAIngresarViandas.verificarEspacioDisponible();
        if (flagHeladera == 0) {
            // si la heladera no tiene lugar tira error
            throw new Error("Espacio no disponible en heladera, seleccione otra!");
        } else {
            // si la heladera tiene lugar al momento de solicitar hacer la colaboracion, se crea,
            // se agrega a la lista de contrib y se crea la solicitud
            // aca se crea una nueva contribucion con estado pendiente (false en entregada)
            Donacion_viandas Contribucion = new Donacion_viandas(this, HeladeraAIngresarViandas, ViandaADonar);
            contribuciones.add(Contribucion);
            Tarjeta.crearSolicitudWebDonacion(HeladeraAIngresarViandas);
        }
    }

    public void concretarDonacionVianda(Heladera heladera) {
        // para cada una de las contribuciones, busca las que son donaciones de vianda para esa heladera
        for (Contribucion contribucion: contribuciones) {
            SolicitudApertura solicitudApertura = Tarjeta.crearSolicitudApertura(heladera);
            // pregunta si es una instancia de donacion de viandas, si no esta finalizada y si el horario es el adecuado
            if (contribucion instanceof Donacion_viandas donacionActual && !donacionActual.isContribucionFinalizada()) {
                // si la heladera de la contribucion es la actual
                if (donacionActual.getHeladera().equals(heladera)) {
                    // si existe una solicitud que matchee con la heladera
                    SolicitudWeb solicitudActual = Tarjeta.buscarSolicitudValida(heladera);
                    if (solicitudActual != null) {
                        if (!heladera.estaLlena()) {
                            donacionActual.realizar_contribucion();
                            donacionActual.setContribucionExitosa(true);
                            donacionActual.setContribucionFinalizada(true);
                            solicitudApertura.setAperturaExitosa(true);

                        } else {
                            donacionActual.setContribucionExitosa(false);
                            donacionActual.setContribucionFinalizada(true);
                            solicitudApertura.setAperturaExitosa(false);
                        }
                        solicitudActual.setFinalizada(true);
                    } solicitudApertura.setAperturaExitosa(false);
                } solicitudApertura.setAperturaExitosa(false);
            } solicitudApertura.setAperturaExitosa(false);
        }
    }
}

