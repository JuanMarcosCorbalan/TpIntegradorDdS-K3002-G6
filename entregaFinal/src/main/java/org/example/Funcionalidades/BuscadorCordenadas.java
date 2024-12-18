package org.example.Funcionalidades;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.example.DAO.PuntoUbicacionDAO;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Utils.BDutils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class BuscadorCordenadas {
    public PuntoUbicacion buscarCoordenadas(String direccion_completa,EntityManager em) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCh_vSKxgkCZrez4CJA-_VaVywEUyUNq_k")
                .build();

        String latitud;
        String longitud;
        PuntoUbicacionDAO puntoUbicaciondao = new PuntoUbicacionDAO(em);
        PuntoUbicacion puntoUbicacion = new PuntoUbicacion();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, direccion_completa).await();
            double lat = results[0].geometry.location.lat;
            double lng = results[0].geometry.location.lng;
            latitud = String.valueOf(lat);
            longitud = String.valueOf(lng);
            puntoUbicacion = puntoUbicaciondao.findOrCreate(latitud,longitud);
            //System.out.println("Latitud: " + lat + ", Longitud: " + lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntoUbicacion;

    }
}
