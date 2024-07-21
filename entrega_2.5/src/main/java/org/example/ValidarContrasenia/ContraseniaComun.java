package org.example.ValidarContrasenia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.ValidarContrasenia.condiciones.CONTRASENIA_EN_EL_TOP;

public class ContraseniaComun extends CondicionContrasenia {

    @Override
    public int validar(String password) throws IOException {
        String nombreArchivo = "src/main/java/org/example/ValidarContrasenia/10-million-password-list-top-10000.txt";
        ArrayList<String> lista = pasarArchivoALista(nombreArchivo);

        for(String contrasenia : lista) {
            if (contrasenia.equals(password))
                return CONTRASENIA_EN_EL_TOP.ordinal();
        }
        return -1;
    }

    public ArrayList<String> pasarArchivoALista(String rutaArchivo) throws IOException {
        BufferedReader entrada = new BufferedReader(new FileReader(rutaArchivo));
        ArrayList<String> lista = new ArrayList<String>();
        String lineaLeida;
        while((lineaLeida = entrada.readLine()) != null) {
            lista.add(lineaLeida);
        }
        return lista;
    }
}
