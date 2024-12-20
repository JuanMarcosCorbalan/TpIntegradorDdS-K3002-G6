package org.example.Heladeras;

import org.example.Colaborador.Colaborador;
import org.example.GeneradorId;
import org.example.Suscripcion.*;
import org.example.Tarjetas.RetiroVianda;
import org.example.Validadores_Sensores.*;

import javax.persistence.*;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Heladera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    private PuntoUbicacion puntoUbicacion;

    @Column(unique = true)
    String idHeladera;
    // CREO QUE NO IRIA MAS
    //int unidadViandasActual;
    int unidadesMaximoViandas; //VIENE DEFINIDA

    @OneToMany(mappedBy = "heladera")
    List<Vianda> viandas = new LinkedList<Vianda>();

    LocalDate FechaFuncionamiento;

    @Enumerated(EnumType.STRING)
    EstadoHeladera estado_actual = EstadoHeladera.INACTIVO; //ACTUALIZACION ENTREGA 2

    @OneToMany(mappedBy = "heladeraRetiro")
    List<RetiroVianda> retiros = new ArrayList<RetiroVianda>();

    @OneToMany(mappedBy = "heladera",cascade = CascadeType.ALL)
    List<Incidente> incidentes = new ArrayList<>();

    @OneToMany(mappedBy = "heladera",cascade = CascadeType.ALL)
    List<Suscripcion> suscripciones = new ArrayList<>();




    int cantidadFallas;

    int temperaturaMaxima;
    int temperaturaMinima;
    Double temperaturaActual;




    int cantidadViandasDonadas;

    @Transient
    ValidadorTemperatura validadorTemp;
    @Transient
    ValidadorMovimiento validadorMov;


    @ManyToOne
    private Colaborador colaboradores;

    //CONSTRUCTORES
    public Heladera(String idHeladera, Double temperaturaActual) {
        this.idHeladera = idHeladera;
        this.temperaturaActual=temperaturaActual;
        this.validadorMov = new ValidadorMovimiento(this);
        this.validadorTemp = new ValidadorTemperatura(this);
    }

    public Heladera(String idHeladera) {
        this.idHeladera = idHeladera;
    }

    public Heladera( PuntoUbicacion puntoUbicacion, LocalDate fechaFuncionamiento, int tempMax, int temMin, Colaborador colaborador, int maxViandas) {
        this.puntoUbicacion = puntoUbicacion;
        this.FechaFuncionamiento = fechaFuncionamiento;
        this.temperaturaMaxima = tempMax;
        this.temperaturaMinima = temMin;
        this.colaboradores = colaborador;
        this.unidadesMaximoViandas = maxViandas;
        this.idHeladera = GeneradorId.generar();
    }

    public void setIdHeladera(String idHeladera){
        this.idHeladera = idHeladera;
    }

    public Heladera(String idHeladera, Integer unidadesMaximoViandas) {
        this.idHeladera = idHeladera;
        this.unidadesMaximoViandas = unidadesMaximoViandas;
    }


    public Heladera(PuntoUbicacion puntoUbicacion ) {
        // habria q generar un id
        this.puntoUbicacion = puntoUbicacion;
    }

    public Heladera(PuntoUbicacion puntoUbicacion,int temperaturaMinima,int temperaturaMaxima,int cantidadViandasMax,Colaborador colaborador) {
        this.idHeladera = GeneradorId.generar();
        this.puntoUbicacion = puntoUbicacion;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.unidadesMaximoViandas = cantidadViandasMax;
        this.colaboradores = colaborador;
    }

    public Heladera() {

    }

    public long getId(){return id;}

    public boolean tieneViandas(){
        return !viandas.isEmpty();
    }

    public void ponerFuncionamiento(){
        estado_actual = EstadoHeladera.ACTIVA;
    }
    public void solicitarMantenimiento(){
        estado_actual = EstadoHeladera.EN_MANTENIMIENTO;
    }
    public boolean requiereMantenimiento(){
        // verificacion sobre los parametros que indiquen si necesita mantenimiento
        return false;
    }
    public void aniadirVianda(Vianda unaVianda){
        viandas.add(unaVianda);
        this.notificar_viandas_faltantes();
        this.notificar_viandas_sobrantes();
    }

    public void quitarVianda(){
        viandas.remove(0);
        this.notificar_viandas_sobrantes();
        this.notificar_viandas_faltantes();
    }

    public void retirarViandas(Integer cantidadARetirar) {
        for (int i = 0; i < cantidadARetirar; i++) {
            viandas.remove(0);
        }
    }

    public void definirTemperatura(int temperaturaMinima, int temperaturaMaxima){
        this.setTemperaturaMaxima(temperaturaMaxima);
        this.setTemperaturaMinima(temperaturaMinima);
    }

    //NUEVO ENTREGA 2
    public void aniadir_retiro(RetiroVianda retiro){
        retiros.add(retiro);
    }

    //SUFRE DESPERFECTO NOTIFICAR, CON EVENTTPYRE "desperfecto"
    public Integer cantidadViandasRetiradas(){
        return retiros.size();
    }

    public void aniadirDonacion(){
        cantidadViandasDonadas++;
    }


    // GETTERS Y SETTERS

    public Double getTemperaturaActual(){return temperaturaActual;}


    public String getIdHeladera() {
        return idHeladera;
    }

    public Integer getCantidadFallas(){
        return cantidadFallas;
    }

    public int getCantidadViandasDonadas() {
        return cantidadViandasDonadas;
    }

    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(int temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(int temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public PuntoUbicacion getPuntoUbicacion() {
        return puntoUbicacion;
    }

    public void setPuntoUbicacion(PuntoUbicacion puntoUbicacion) {
        this.puntoUbicacion = puntoUbicacion;
    }

    public void desactivar(){this.estado_actual = EstadoHeladera.INACTIVO;}
    public void activar(){this.estado_actual = EstadoHeladera.ACTIVA;}

    //public AdministradorSuscripciones getAdmin_suscr() {return admin_suscr;}

    //public void setAdmin_suscr(AdministradorSuscripciones admin){this.admin_suscr = admin;}

    public int getCantidadViandasActuales(){return viandas.size();}

    public int verificarEspacioUnitarioDisponible(){
       if (unidadesMaximoViandas - getCantidadViandasActuales() > 0) {
           return 1;
       } else {
           return 0;
       }
    }

    public Boolean tieneEspacioDisponible(Integer cantidad) {
       return getCantidadViandasActuales() + cantidad < unidadesMaximoViandas;
    }

    public boolean tieneCantidadDeViandas(Integer cantidad) {
        return getCantidadViandasActuales() - cantidad >= 0;
    }

    public boolean estaLlena(){
        return this.getCantidadViandasActuales() == this.unidadesMaximoViandas;
    }

    public void notificar_viandas_sobrantes()  {
        int viandasActuales = getCantidadViandasActuales();
        notificarViandasSobrantes(viandasActuales);
    }
    public void notificar_viandas_faltantes() {
        int viandasActuales = getCantidadViandasActuales();
        int viandas_faltantes = unidadesMaximoViandas - viandasActuales;
        notificarViandasFaltantes(viandas_faltantes);
    }
    public void notificar_desperfecto()  {
        notificarDesperfecto();
    }

    public void procesar_Alerta(Alerta alerta)
    {
        switch(alerta.getTipoAlerta()){
            case ALERT_TEMPERATURA -> {
                validadorTemp.darAviso(TipoAlerta.ALERT_TEMPERATURA);
                desactivar();
            }
            case ALERT_FRAUDE -> {
                validadorMov.darAviso(TipoAlerta.ALERT_FRAUDE);
                desactivar();
            }
            case ALERT_FALLA_CONEX -> desactivar();

        }
    }

    public void procesar_temperatura(Double temperatura)
    {
            temperaturaActual = temperatura;
    }

    public EstadoHeladera getEstado(){return estado_actual;}

    public ValidadorMovimiento getValidadorMov(){return validadorMov;}

    public ValidadorTemperatura getValidadorTemp() {
        return validadorTemp;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }



    // Getter para unidadesMaximoViandas
    public int getUnidadesMaximoViandas() {
        return unidadesMaximoViandas;
    }

    // Getter para FechaFuncionamiento
    public LocalDate getFechaFuncionamiento() {
        return FechaFuncionamiento;
    }



    //SUSCRIPCIONES

    public void suscribirse(Suscripcion suscripcion){suscripciones.add(suscripcion);
    }

    public void desuscribirse(Suscripcion suscripcion){suscripciones.remove(suscripcion);}

    public void notificarViandasFaltantes(Integer cantidadViandas)  {
        //ITERO SOBRE LAS CLAVES, BUSCANDO LOS EVENTOS IGUALES
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof FaltanNViandas suscripcionFaltante)
            {
                if(suscripcionFaltante.getCantViandas().equals(cantidadViandas))
                {
                    suscripcionFaltante.darAviso();
                }
            }
        }

    }

    public void notificarViandasSobrantes(Integer cantidadViandas) {
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof QuedanNViandas suscripcionSobrante)
            {
                if(suscripcionSobrante.getCantViandas().equals(cantidadViandas))
                {
                    suscripcionSobrante.darAviso();
                }
            }
        }
    }

    public void notificarDesperfecto()  {
        for(Suscripcion suscripcion : suscripciones)
        {
            if(suscripcion instanceof AvisoPorDesperfecto suscripcionDesperfecto)
            {
                suscripcionDesperfecto.darAviso();
            }
        }
    }

    public String getNombre() {
        return puntoUbicacion.getNombre();
    }

}
