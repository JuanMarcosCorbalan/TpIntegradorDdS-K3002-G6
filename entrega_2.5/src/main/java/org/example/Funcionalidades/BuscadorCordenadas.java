package org.example.Funcionalidades;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.example.Heladeras.PuntoUbicacion;

public class BuscadorCordenadas {
    public PuntoUbicacion buscarCoordenadas(String direccion_completa) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCh_vSKxgkCZrez4CJA-_VaVywEUyUNq_k")
                .build();

        PuntoUbicacion puntoUbicacion = new PuntoUbicacion();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, direccion_completa).await();
            double lat = results[0].geometry.location.lat;
            double lng = results[0].geometry.location.lng;
            puntoUbicacion.setLatitud(String.valueOf(lat));
            puntoUbicacion.setLongitud(String.valueOf(lng));
            //System.out.println("Latitud: " + lat + ", Longitud: " + lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntoUbicacion;

    }
}
