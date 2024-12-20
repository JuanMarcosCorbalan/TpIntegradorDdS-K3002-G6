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
            PuntoUbicacion nuevo_punto = new PuntoUbicacion(latitudString, longitudString);
            puntosSugeridos.add(nuevo_punto);
        }
    }

    public List<PuntoUbicacion> solicitar_ubicaciones()
    {
        List<PuntoUbicacion> puntos = new ArrayList<>();
        APICallPuntosSugeridos new_apicall = new APICallPuntosSugeridos();
        String data_recibida = new_apicall.getPuntosSugeridos();

        JSONArray jsonArray = new JSONArray(data_recibida);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            String longitudString = jsonObject.optString("longitud");
            String latitudString = jsonObject.optString("latitud");

            PuntoUbicacion nuevo_punto = new PuntoUbicacion(latitudString, longitudString);
            //nuevo_punto.completarUbicacion();
            nuevo_punto.completarDireccion();
            puntos.add(nuevo_punto);
        }
        return puntos;
    }

    public List<PuntoUbicacion> getPuntosSugeridos() {
        return puntosSugeridos;
    }

    //PRUEBA DE LOS PUNTOS SUGERIDOS
    public static void main(String[]args)
    {
        BusquedaPuntosSugeridos nueva_busqueda = new BusquedaPuntosSugeridos();
        /*nueva_busqueda.solicitar_puntos_sugeridos();
        for (PuntoUbicacion punto : nueva_busqueda.puntosSugeridos) {
            System.out.println(punto.getLatitud().toString());
            System.out.println(punto.getLongitud().toString());
        }*/
        List <PuntoUbicacion> puntos = nueva_busqueda.solicitar_ubicaciones();
        for (PuntoUbicacion punto : puntos) {
            System.out.println(punto.getLatitud());
            System.out.println(punto.getLongitud());
            System.out.println(punto.getDireccion());
            System.out.println(punto.getLocalidad().getNombre());
        }
    }


}
