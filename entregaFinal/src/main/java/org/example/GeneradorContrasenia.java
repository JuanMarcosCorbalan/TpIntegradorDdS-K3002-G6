package org.example;

import java.security.SecureRandom;

public class GeneradorContrasenia {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+";

    public static String generarContrasenia(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder contrasenia = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            contrasenia.append(CARACTERES.charAt(index));
        }

        return contrasenia.toString();
    }
}
