package org.example.Colaborador;

import org.example.Formas_contribucion.*;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;
import org.example.Ofertas.Oferta;
import org.example.Ofertas.OfertaCanjeada;
import org.example.Persona.Persona;
import org.example.Persona.Rol;
import org.example.Suscripcion.*;
import org.example.Personal.Tecnico;
import org.example.Tarjetas.SolicitudApertura;
import org.example.Tarjetas.TarjetaColaborador;
import org.example.Validadores_Sensores.FallaTecnica;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Colaborador extends Rol {

    @OneToMany(mappedBy = "colaborador",cascade = CascadeType.ALL)
    List<Contribucion> contribuciones = new ArrayList<Contribucion>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<Forma_colaborar> formas_de_colaborar;
    //List<Heladera> heladeras_a_cargo;

    double  puntos;
    //List<Heladera> heladeras_a_cargo;
    Integer viandasDonadas = 0;

    //@Transient
    @OneToOne(cascade = CascadeType.ALL)
    TarjetaColaborador tarjetaColaborador;

    @OneToMany(mappedBy = "colaborador")
    List<MensajeAviso> mensajesAvisos;

    @OneToMany(mappedBy = "colaborador",cascade = CascadeType.ALL)
    List<OfertaCanjeada> ofertasCanjeadas;

    /*@ManyToMany
    @JoinTable(
            name = "FormasColaborarxRol",
            joinColumns = @JoinColumn(name = "ID_Colaborador"),
            inverseJoinColumns = @JoinColumn(name = "ID_FormasColaborar")
    )*/
    //@Enumerated(EnumType.STRING) // Almacenamos el enum como String en la BD.
    @Transient
    Forma_colaborar formasColaborar;


    Integer tarjetasARepartir = 0;

    public Colaborador() {

    }


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
        puntos+= nuevaContribucion.calcular_puntos();
    }
    /*
    public void realizar_contribucion(){
        Contribucion contribucion_a_realizar = contribuciones.removeFirst();
        contribucion_a_realizar.realizar_contribucion();
    }*/

    public Colaborador(Persona persona, List<Forma_colaborar> formas)
    {
        this.persona = persona;
        this.contribuciones = new ArrayList<Contribucion>();
        this.formas_de_colaborar = formas;
        //this.heladeras_a_cargo = new ArrayList<Heladera>();
        this.tarjetasARepartir = 0;
        this.tarjetaColaborador = new TarjetaColaborador(this);
    }

    public Colaborador(Persona persona_colaboradora) {this.persona = persona_colaboradora; this.tarjetaColaborador = new TarjetaColaborador(this);}

    public double getPuntos() {
        double puntos = 0;
        
        for (Contribucion contribucion : contribuciones)
        {
            puntos+= contribucion.calcular_puntos();
        }
        this.puntos = puntos;
        return puntos;
    }

    public double obtenerPuntos(){
        return puntos;
    }

    public void canjearOferta(Oferta oferta)
    {
        if(oferta.getPuntosNecesarios()<=obtenerPuntos())
        {
            Integer puntos = oferta.getPuntosNecesarios();
            oferta.canjear();
            this.restarPuntos(puntos);
            OfertaCanjeada ofertaCanjeada = new OfertaCanjeada(oferta.getNombre(),puntos, LocalDate.now(),this);
            ofertasCanjeadas.add(ofertaCanjeada);
        }
        //QUE HAGA ALGO SI NO PUEDE CANJEAR
    }

    public void restarPuntos(double puntos){
        this.puntos -= puntos;
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

    public void asignarTarjeta(TarjetaColaborador tj){
        this.tarjetaColaborador = tj;
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

    public void solicitarDonacionVianda(Heladera HeladeraAIngresarViandas, Vianda ViandaADonar){

        int flagHeladera = HeladeraAIngresarViandas.verificarEspacioUnitarioDisponible();
        if (flagHeladera == 0) {
            // si la heladera no tiene lugar tira error
            throw new Error("Espacio no disponible en heladera, seleccione otra!");
        } else {
            // si la heladera tiene lugar al momento de solicitar hacer la colaboracion, se crea,
            // se agrega a la lista de contrib y se crea la solicitud
            // aca se crea una nueva contribucion con estado pendiente (false en entregada)
            Donacion_viandas Contribucion = new Donacion_viandas(this, HeladeraAIngresarViandas, ViandaADonar);
            ViandaADonar.setHeladera(HeladeraAIngresarViandas);
            contribuciones.add(Contribucion);
            puntos+= Contribucion.calcular_puntos();
            tarjetaColaborador.crearSolicitudWebDonacion(HeladeraAIngresarViandas);
        }
    }

    public void concretarDonacionVianda(Heladera heladera) {
        // para cada una de las contribuciones, busca las que son donaciones de vianda para esa heladera
        for (Contribucion contribucion: contribuciones) {
            SolicitudApertura solicitudApertura = tarjetaColaborador.crearSolicitudApertura(heladera);
            tarjetaColaborador.verificarApertura(heladera, contribucion, solicitudApertura);
        }
    }


    public void solicitarDistribucionViandas(Heladera HeladeraOrigen, Heladera HeladeraDestino, Integer cantidadViandasAMover , Motivo_distribucion motivo_distribucion){
        int flagHeladeraOrigen = HeladeraOrigen.verificarEspacioUnitarioDisponible();
        Boolean flagHeladeraDestino = HeladeraDestino.tieneEspacioDisponible(cantidadViandasAMover);
        if (flagHeladeraOrigen == 0 || !flagHeladeraDestino) {
            // si la heladera origen no tiene la suficiente cantidad de
            // viandas o la destino no tiene lugar tira error
            throw new Error("Cantidad de viandas a mover invalida!");
        } else {
            // si la heladera origen tiene las viandas disp y si
            // la heladera destino tiene lugar al momento de solicitar hacer la colaboracion,
            // se crea, se agrega a la lista de contrib y se crea la solicitud
            // aca se crea una nueva contribucion con estado pendiente (false en entregada)
            Distribucion_viandas Contribucion = new Distribucion_viandas(cantidadViandasAMover,this, HeladeraOrigen, HeladeraDestino, motivo_distribucion);
            contribuciones.add(Contribucion);
            puntos+= Contribucion.calcular_puntos();
            tarjetaColaborador.crearSolicitudesWebDistribucion(HeladeraOrigen,HeladeraDestino);
        }
    }


    public void concretarDistribucionVianda(Heladera heladeraOrigen, Heladera heladeraDestino) {
        // para cada una de las contribuciones, busca las que son retiros para esa heladera
        for (Contribucion contribucion: contribuciones) {
            SolicitudApertura solicitudAperturaOrigen = tarjetaColaborador.crearSolicitudApertura(heladeraOrigen);
            tarjetaColaborador.verificarApertura(heladeraOrigen, contribucion, solicitudAperturaOrigen);
            SolicitudApertura solicitudAperturaDestino = tarjetaColaborador.crearSolicitudApertura(heladeraDestino);
            tarjetaColaborador.verificarApertura(heladeraDestino, contribucion, solicitudAperturaDestino);
        }
    }

    public void solicitarTarjetasParaRepartir(Integer cantidadTarjetas){
        RegistrarPersonasSV registroPersonasSV = new RegistrarPersonasSV(cantidadTarjetas, LocalDate.now(), this);
        // enviar tarjetas qcyo algo asi
        contribuciones.add(registroPersonasSV);
        puntos+= registroPersonasSV.calcular_puntos();
        if (tarjetasARepartir == null) {
            tarjetasARepartir = 0;
        }
        tarjetasARepartir += cantidadTarjetas;
    }

    public TarjetaColaborador getTarjetaColaborador(){
        return tarjetaColaborador;
    }

    public void registrarPersonaSv(String nombre, String apellido, boolean situacionCalle, String domicilioString, Integer menoresACargo){
        for(Contribucion contribucion: contribuciones) {
            if (contribucion instanceof RegistrarPersonasSV registroPersonasSV && contribucion.getEstado().equals(EstadoContribucion.EN_CURSO) ){
                registroPersonasSV.cargarDatosPersonaSv(nombre, apellido, situacionCalle, domicilioString, menoresACargo);
                registroPersonasSV.asignarTarjeta(this);
                tarjetasARepartir -= 1;
                return;
            }
        }
    }

    public Integer getTarjetasARepartir() {
        return tarjetasARepartir;
    }

    public void solicitarHacerseCargoHeladera(){
        HacerseCargoHeladera cargoHeladera = new HacerseCargoHeladera(this);
        cargoHeladera.hacerseCargo();
        contribuciones.add(cargoHeladera);
    }

    public void setTarjetaColaborador(TarjetaColaborador tarjetaColaborador) {
        this.tarjetaColaborador = tarjetaColaborador;
    }

    public void donarMonto(Integer monto) {
        Donacion_dinero donacionDinero = new Donacion_dinero(monto, Tipos_frecuencia.DONACION_UNICA,this);
        contribuciones.add(donacionDinero);
        puntos+= donacionDinero.calcular_puntos();
    }

    public long getId() {
        return id;
    }

    public List<OfertaCanjeada> getOfertasCanjeadas() {
        return ofertasCanjeadas;
    }
}

