package org.example.Ofertas;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

public class GeneradorCodigoCanje {

    private static final String ALFANUMERICO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarCodigoUnico() {
        // Agregar un prefijo basado en el tiempo actual
        String timestamp = Long.toString(Instant.now().toEpochMilli(), 36); // Base 36 para reducir longitud

        // Generar una cadena aleatoria
        StringBuilder codigoAleatorio = new StringBuilder();
        for (int i = 0; i < 6; i++) { // Longitud adicional del código aleatorio
            int index = RANDOM.nextInt(ALFANUMERICO.length());
            codigoAleatorio.append(ALFANUMERICO.charAt(index));
        }

        // Combinar el timestamp con la cadena aleatoria
        return timestamp + "-" + codigoAleatorio;
    }
}