package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.DAO.LocalidadDAO;
import org.example.Funcionalidades.BuscadorCordenadas;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Heladera heladeraElegida;

    @Transient
    RepositorioHeladeras repositorioHeladeras;

    public HacerseCargoHeladera() {

    }

    public void hacerseCargo() {
        BusquedaPuntosSugeridos busquedaPuntosSugeridos = new BusquedaPuntosSugeridos();
        PuntoUbicacion puntoSeleccionado = this.sugerirPuntoODomicilio(busquedaPuntosSugeridos.getPuntosSugeridos());
        heladeraElegida = new Heladera(puntoSeleccionado);
        puntoSeleccionado.aniadirHeladera(heladeraElegida);
        repositorioHeladeras.aniadirHeladera(heladeraElegida);

    }

    public Heladera hacerseCargoSinApi(String nombre_heladera,int temMin,int temMax,int cantViandas,EntityManager em) {
        BuscadorCordenadas buscadorCordenadas = new BuscadorCordenadas();
        String direccion = colaborador.getPersona_colaboradora().getDomicilio().getDireccion();

        LocalidadDAO ldao = new LocalidadDAO(em);
        String n_localidad = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getNombre();
        String n_ciudad = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getCiudad().getNombre();
        String n_pais = colaborador.getPersona_colaboradora().getDomicilio().getLocalidad().getCiudad().getPais().getNombre();
        Localidad localidad = ldao.findOrCreate(n_localidad,n_ciudad,n_pais);

        //obtener latitudes
        String direccion_Completa = direccion+","+n_localidad+","+n_ciudad+","+n_pais;
        System.out.println(direccion_Completa);
        PuntoUbicacion puntoSeleccionado = buscadorCordenadas.buscarCoordenadas(direccion_Completa+","+n_localidad+","+n_ciudad+","+n_pais);

        puntoSeleccionado.setDireccion(direccion);
        puntoSeleccionado.setNombre(nombre_heladera);
        puntoSeleccionado.setLocalidad(localidad);
        this.heladeraElegida = new Heladera(puntoSeleccionado,temMin,temMax,cantViandas,colaborador);

        puntoSeleccionado.aniadirHeladera(heladeraElegida);
       // repositorioHeladeras.aniadirHeladera(heladeraElegida);

        return heladeraElegida;

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
