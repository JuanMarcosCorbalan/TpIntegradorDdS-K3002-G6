package org.example.ValidarContrasenia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ContraseniaComun extends CondicionContrasenia {

    @Override
    public boolean validar(String password) throws IOException {

        String ruta_absoluta_archivo = "src/main/java/org/example/ValidarContrasenia/10-million-password-list-top-10000.txt";
        ArrayList<String> lista = pasarArchivoALista(ruta_absoluta_archivo);

        for(String contrasenia : lista) {
            if (contrasenia.equals(password))
            {
                System.out.println(MensajeAviso.IN_TOP_TEN_THOUSAND.obtenerAdvertencia());
                return false;
            }

        }
        return true;
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
