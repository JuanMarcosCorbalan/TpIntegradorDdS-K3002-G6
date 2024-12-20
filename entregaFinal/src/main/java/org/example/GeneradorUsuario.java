package org.example;

import java.util.Random;

public class GeneradorUsuario {
    public static String generarNombreUsuario(String nombre, String apellido) {
        String baseUsuario = nombre.toLowerCase().charAt(0) + apellido.toLowerCase();
        int numeroAleatorio = new Random().nextInt(9000) + 1000; // Número aleatorio de 4 dígitos
        return baseUsuario + numeroAleatorio;
    }
}
