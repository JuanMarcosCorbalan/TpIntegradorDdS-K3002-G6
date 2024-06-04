package org.example.Funcionalidades;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusquedaPuntosSugeridos {

    List<Punto> puntos_sugeridos = new ArrayList<Punto>();

    public void solicitar_puntos_sugeridos()
    {
        APICallPuntosSugeridos new_apicall = new APICallPuntosSugeridos();
        String data_recibida = new_apicall.getPuntosSugeridos();

        JSONArray jsonArray = new JSONArray(data_recibida);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            String longitud = jsonObject.optString("longitud");
            String latitud = jsonObject.optString("latitud");
            Punto nuevo_punto = new Punto();
            nuevo_punto.latitud = latitud;
            nuevo_punto.longitud = longitud;
            puntos_sugeridos.add(nuevo_punto);
        }
    }

    //PRUEBA DE LOS PUNTOS SUGERIDOS
    public static void main(String[]args)
    {
        BusquedaPuntosSugeridos nueva_busqueda = new BusquedaPuntosSugeridos();
        nueva_busqueda.solicitar_puntos_sugeridos();
        for (Punto punto : nueva_busqueda.puntos_sugeridos) {
            System.out.println(punto);
        }
    }

}
