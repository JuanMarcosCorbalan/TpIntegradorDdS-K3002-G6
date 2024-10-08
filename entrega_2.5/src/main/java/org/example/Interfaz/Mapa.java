package org.example.Interfaz;

import java.util.List;

public class Mapa {


    /*List<Heladera> heladeras = new ArrayList<Heladera>();

    // creo que esto no va pero lo dejo por ahora
    public Heladera seleccionarHeladera(Heladera unaHeladera){
        Integer indiceHeladera = heladeras.indexOf(unaHeladera);
        if(indiceHeladera == -1){
            throw new Error("No existe esta heladera");
        }
        Heladera heladeraDeLista = heladeras.get(indiceHeladera);
        return heladeraDeLista;
    }*/

    public void aniadir_marcador(String latitud, String longitud, List<String> marcadores)
    {
        String new_marker = "&markers=color:orange%7Clabel:H%7C"+latitud+","+longitud;
        marcadores.add(new_marker);
    }

}
