package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;
import org.example.Funcionalidades.BusquedaPuntosSugeridos;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Heladeras.RepositorioHeladeras;

import javax.persistence.*;
import java.util.List;


@Entity
public class HacerseCargoHeladera extends Contribucion{

    @OneToOne(cascade = CascadeType.ALL)
    private Heladera heladeraElegida;

    @Transient
    RepositorioHeladeras repositorioHeladeras;

    public void hacerseCargo() {
        BusquedaPuntosSugeridos busquedaPuntosSugeridos = new BusquedaPuntosSugeridos();
        PuntoUbicacion puntoSeleccionado = this.sugerirPuntoODomicilio(busquedaPuntosSugeridos.getPuntosSugeridos());
        heladeraElegida = new Heladera(puntoSeleccionado);
        puntoSeleccionado.aniadirHeladera(heladeraElegida);
        repositorioHeladeras.aniadirHeladera(heladeraElegida);

    }

    public PuntoUbicacion sugerirPuntoODomicilio(List<PuntoUbicacion> puntosSugeridos){
        System.out.println("Seleccione el punto deseado para colocar la heladera");
        // aca deberia mostrar los puntos q pasa por parametro pero voy a generar uno a partir de dos string lat y long
        System.out.println("Seleccione latitud para colocar la heladera");
        String latitudString = System.console().readLine();
        System.out.println("Seleccione longitud para colocar la heladera");
        String longitudString = System.console().readLine();

        return new PuntoUbicacion(Integer.getInteger(latitudString), Integer.getInteger(longitudString));
    };

    public HacerseCargoHeladera(Colaborador colaborador) {
        super(colaborador);
    }

    @Override
    public double calcular_puntos() {
        return super.calcular_puntos();
    }
}
