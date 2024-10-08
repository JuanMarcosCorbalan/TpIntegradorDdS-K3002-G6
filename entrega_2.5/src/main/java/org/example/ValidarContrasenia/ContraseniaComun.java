package org.example.ValidarContrasenia;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ContraseniaComun extends CondicionContrasenia {

    static String ruta_absoluta_archivo = "src/main/java/org/example/ValidarContrasenia/10-million-password-list-top-10000.txt";
    static ArrayList<String> contrasenias_comunes;

    @Override
    public boolean validar(String password) throws IOException {

        for(String contrasenia : contrasenias_comunes) {
            if (contrasenia.equals(password))
            {
                System.out.println(MensajeAviso.IN_TOP_TEN_THOUSAND.obtenerAdvertencia());
                return false;
            }

        }
        return true;
    }

    //PONER ACA LO DE QUE CUANDO SE INSTANCIA ESTA CLASE COPIAR EL ARCHIVO A UNA ESTRUCTURA.
    ContraseniaComun () throws IOException {
        contrasenias_comunes = pasarArchivoALista(ruta_absoluta_archivo);
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
