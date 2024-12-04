package org.example.Heladeras;

import org.example.Colaborador.Colaborador;
import org.example.Suscripcion.AdministradorSuscripciones;
import org.example.Tarjetas.RetiroVianda;
import org.example.Validadores_Sensores.*;

import javax.persistence.*;
import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Heladera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private PuntoUbicacion puntoUbicacion;

    @Transient
    String idHeladera; // CREO QUE NO IRIA MAS
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

    int cantidadFallas;

    int temperaturaMaxima;
    int temperaturaMinima;
    Double temperaturaActual;

    @Transient
    AdministradorSuscripciones admin_suscr = new AdministradorSuscripciones();

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

    public Heladera(String idHeladera, PuntoUbicacion puntoUbicacion, LocalDate fechaFuncionamiento, int tempMax, int temMin, Colaborador colaborador ) {
        this.idHeladera = idHeladera;
        this.puntoUbicacion = puntoUbicacion;
        this.FechaFuncionamiento = fechaFuncionamiento;
        this.temperaturaMaxima = tempMax;
        this.temperaturaMinima = temMin;
        this.colaboradores = colaborador;
    }


    public Heladera(String idHeladera, Integer unidadesMaximoViandas) {
        this.idHeladera = idHeladera;
        this.unidadesMaximoViandas = unidadesMaximoViandas;
    }


    public Heladera(PuntoUbicacion puntoUbicacion ) {
        // habria q generar un id
        this.puntoUbicacion = puntoUbicacion;
    }

    public Heladera() {

    }


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

    public AdministradorSuscripciones getAdmin_suscr() {return admin_suscr;}

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

    public void notificar_viandas_sobrantes() {
        int viandasActuales = getCantidadViandasActuales();
        admin_suscr.notificar("Quedan"+Integer.toString(viandasActuales)+"Viandas");
    }
    public void notificar_viandas_faltantes() {
        Integer viandasActuales = getCantidadViandasActuales();
        int viandas_faltantes = (Integer) unidadesMaximoViandas - viandasActuales;
        admin_suscr.notificar("Faltan"+Integer.toString(viandas_faltantes) +"Viandas");
    }
    public void notificar_desperfecto()
    {
        admin_suscr.notificar("Aviso-Desperfecto");
    }

    public void procesar_Alerta(Alerta alerta)
    {
        switch(alerta.getTipo()){
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
}
