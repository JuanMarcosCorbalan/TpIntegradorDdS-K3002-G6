package org.example.ValidarContrasenia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContraseniaComun extends CondicionContrasenia {

    @Override
    public boolean validar(String password){
        String nombreArchivo = "src/10-million-password-list-top-10000.txt";
        BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo));
        String lineaLeida;
        while((lineaLeida = entrada.readLine()) != null) {
            if(lineaLeida.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
