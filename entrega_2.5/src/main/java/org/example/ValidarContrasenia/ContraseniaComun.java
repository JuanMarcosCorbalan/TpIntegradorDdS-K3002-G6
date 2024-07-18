package org.example.ValidarContrasenia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContraseniaComun extends CondicionContrasenia {

    @Override
    public boolean validar(String password) throws IOException {
        String nombreArchivo = "src/main/java/org/example/ValidarContrasenia/10-million-password-list-top-10000.txt";
        BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo));
        String lineaLeida;
        while((lineaLeida = entrada.readLine()) != null) {
            if(lineaLeida.equals(password)) {
                System.out.print("LA CONTRASEÑA ESTA EN EL TOP DE CONTRASEÑAS DEBILES");
                return false;
            }
        }
        return true;
    }
}
