package org.example.Funcionalidades;

import org.example.Heladeras.PuntoUbicacion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusquedaPuntosSugeridos {

    List<PuntoUbicacion> puntosSugeridos = new ArrayList<PuntoUbicacion>();

    public void solicitar_puntos_sugeridos()
    {
        APICallPuntosSugeridos new_apicall = new APICallPuntosSugeridos();
        String data_recibida = new_apicall.getPuntosSugeridos();

        JSONArray jsonArray = new JSONArray(data_recibida);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            String longitudString = jsonObject.optString("longitud");
            String latitudString = jsonObject.optString("latitud");
            int longitudInt = Integer.parseInt(longitudString);
            int latitudInt = Integer.parseInt(latitudString);
            PuntoUbicacion nuevo_punto = new PuntoUbicacion(latitudInt, longitudInt);
            puntosSugeridos.add(nuevo_punto);
        }
    }

    public List<PuntoUbicacion> getPuntosSugeridos() {
        return puntosSugeridos;
    }

    //PRUEBA DE LOS PUNTOS SUGERIDOS
    public static void main(String[]args)
    {
        BusquedaPuntosSugeridos nueva_busqueda = new BusquedaPuntosSugeridos();
        nueva_busqueda.solicitar_puntos_sugeridos();
        for (PuntoUbicacion punto : nueva_busqueda.puntosSugeridos) {
            System.out.println(punto.getLatitud().toString());
            System.out.println(punto.getLongitud().toString());
        }
    }


}
