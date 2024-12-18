package org.example.Funcionalidades;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;


public class BuscadorDireccion {
    public String buscarDireccion(String lat, String lng) {
        // Configura el contexto con tu clave de API
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCh_vSKxgkCZrez4CJA-_VaVywEUyUNq_k")
                .build();

        // Coordenadas de ejemplo
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lng);

        LatLng coordenadas = new LatLng(latitude, longitude);
        String direccion;
        try {
            // Solicitud de geocodificación inversa
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, coordenadas).await();

            if (results.length > 0) {
                direccion = results[0].formattedAddress;
                //System.out.println("Dirección: " + direccion);
            } else {
                direccion = null;
                System.out.println("No se encontraron resultados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            direccion = null;
        }
        return direccion;
    }

    public String buscarDireccion2(String coordenadas) {
        // Configura el contexto con tu clave de API
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCh_vSKxgkCZrez4CJA-_VaVywEUyUNq_k")
                .build();

        // Dividir la cadena de coordenadas en latitud y longitud
        String[] partes = coordenadas.split(",");

        if (partes.length != 2) {
           // System.out.println("Formato de coordenadas incorrecto");
            return null;
        }

        // Convertir latitud y longitud a double
        double latitude = Double.parseDouble(partes[0].trim());  // trim() para quitar espacios si los hay
        double longitude = Double.parseDouble(partes[1].trim());

        // Crear el objeto LatLng con las coordenadas
        LatLng latLng = new LatLng(latitude, longitude);

        String direccion;
        try {
            // Solicitud de geocodificación inversa
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, latLng).await();

            if (results.length > 0) {
                direccion = results[0].formattedAddress;
                //System.out.println("Dirección: " + direccion);
            } else {
                direccion = null;
               // System.out.println("No se encontraron resultados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            direccion = null;
        }
        return direccion;
    }


    public String buscarBarrio(String direccionCompleta) {
        // Configura el contexto con tu clave de API
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCh_vSKxgkCZrez4CJA-_VaVywEUyUNq_k")
                .build();

        try {
            // Dirección de ejemplo

            // Solicita la geocodificación
            GeocodingResult[] results = GeocodingApi.geocode(context, direccionCompleta).await();

            if (results.length > 0) {
                // Itera sobre los componentes de la dirección
                for (AddressComponent component : results[0].addressComponents) {
                    for (AddressComponentType type : component.types) {
                        // Busca el componente del barrio
                        if (type == AddressComponentType.NEIGHBORHOOD || type == AddressComponentType.SUBLOCALITY) {
                            return component.longName;
                            //System.out.println("Barrio: " + component.longName);
                        }
                    }
                }
            } else {
                System.out.println("No se encontraron resultados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BuscadorDireccion buscador = new BuscadorDireccion();
        String direccion = buscador.buscarDireccion("-34.608787","-58.435696");


        System.out.println("Direcicon: " + direccion);
        System.out.println("Barrio: " + buscador.buscarBarrio(direccion));

    }
}
