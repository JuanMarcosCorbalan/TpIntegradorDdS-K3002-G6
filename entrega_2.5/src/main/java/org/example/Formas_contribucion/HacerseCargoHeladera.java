package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.DAO.HeladeraDAO;
import org.example.DAO.LocalidadDAO;
import org.example.DAO.PuntoUbicacionDAO;
import org.example.Funcionalidades.BuscadorCordenadas;
import org.example.Funcionalidades.BuscadorDireccion;
import org.example.Funcionalidades.BusquedaPuntosSugeridos;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Heladeras.RepositorioHeladeras;
import org.example.Persona.Localidad;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static org.example.Formas_contribucion.EstadoContribucion.EXITOSA;


@Entity
public class HacerseCargoHeladera extends Contribucion{

    @OneToOne
    private Heladera heladeraElegida;

    @Transient
    RepositorioHeladeras repositorioHeladeras;

    public HacerseCargoHeladera() {

    }

    public void hacerseCargoConApi(String nombre_heladera,Integer temMin,Integer temMax,Integer cantViandas,String datosPuntoUbicacion,EntityManager em) {
        PuntoUbicacionDAO puntoUbicacionDAO = new PuntoUbicacionDAO(em);

        PuntoUbicacion puntoSeleccionado = new PuntoUbicacion(datosPuntoUbicacion);
        puntoSeleccionado.completarUbicacion(em);


        puntoSeleccionado.setNombre(nombre_heladera);
        puntoUbicacionDAO.save(puntoSeleccionado);

        HeladeraDAO heladeraDAO = new HeladeraDAO(em);
        Heladera heladera = new Heladera(puntoSeleccionado,temMin,temMax,cantViandas,colaborador);
        heladeraDAO.save(heladera);



        puntoSeleccionado.aniadirHeladera(heladeraElegida);
        puntoUbicacionDAO.update(puntoSeleccionado);
        // repositorioHeladeras.aniadirHeladera(heladeraElegida);
        this.heladeraElegida = heladera;
        //return heladeraElegida;
    }

    public void hacerseCargoSinApi(String nombre_heladera,Integer temMin,Integer temMax,Integer cantViandas,EntityManager em) {
        BuscadorCordenadas buscadorCordenadas = new BuscadorCordenadas();
        String direccion = colaborador.getPersona_colaboradora().getDomicilio().getDireccion();
        PuntoUbicacionDAO puntoUbicacionDAO = new PuntoUbicacionDAO(em);

        LocalidadDAO ldao = new LocalidadDAO(em);
        String n_localidad = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getNombre();
        String n_ciudad = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getCiudad().getNombre();
        String n_pais = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getCiudad().getPais().getNombre();
        Localidad localidad = ldao.findOrCreate(n_localidad,n_ciudad,n_pais);

        //obtener latitudes
        String direccion_Completa = direccion+","+n_localidad+","+n_ciudad+","+n_pais;
        System.out.println(direccion_Completa);
        PuntoUbicacion puntoSeleccionado = buscadorCordenadas.buscarCoordenadas(direccion_Completa,em);

        puntoSeleccionado.setDireccion(direccion);
        puntoSeleccionado.setNombre(nombre_heladera);
        puntoSeleccionado.setLocalidad(localidad);
        puntoUbicacionDAO.save(puntoSeleccionado);
        HeladeraDAO heladeraDAO = new HeladeraDAO(em);
        Heladera heladera = new Heladera(puntoSeleccionado,temMin,temMax,cantViandas,colaborador);
        heladeraDAO.save(heladera);

        puntoSeleccionado.aniadirHeladera(heladeraElegida);
        puntoUbicacionDAO.update(puntoSeleccionado);

        this.heladeraElegida = heladera;

        // repositorioHeladeras.aniadirHeladera(heladeraElegida);

        //return heladeraElegida;
    }

    public PuntoUbicacion sugerirPuntoODomicilio(List<PuntoUbicacion> puntosSugeridos){
        System.out.println("Seleccione el punto deseado para colocar la heladera");
        // aca deberia mostrar los puntos q pasa por parametro pero voy a generar uno a partir de dos string lat y long
        System.out.println("Seleccione latitud para colocar la heladera");
        String latitudString = System.console().readLine();
        System.out.println("Seleccione longitud para colocar la heladera");
        String longitudString = System.console().readLine();

        return new PuntoUbicacion(latitudString, longitudString);
    };

    public HacerseCargoHeladera(Colaborador colaborador) {
        super(colaborador);
        this.fecha_contribucion = LocalDate.now();
        this.estado = EXITOSA;
    }

    @Override
    public double calcular_puntos() {
        return super.calcular_puntos();
    }

    public Heladera getHeladera(){
        return heladeraElegida;
    }


}
