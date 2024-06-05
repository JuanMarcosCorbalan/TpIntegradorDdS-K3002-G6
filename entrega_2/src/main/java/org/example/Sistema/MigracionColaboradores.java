package org.example.Sistema;

import org.example.Colaborador.*;
import org.example.Formas_contribucion.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MigracionColaboradores {

    String archivoCsv = "csvs/csvColaboradores.csv";
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    Tipo_documento tipoDocumento = null;

    public void leerCsv(List<Colaborador> colaboradoresExistentes) {
        // mapeo para tener las personas en vez de colaboradores
        Map<String, Colaborador> colaboradoresExistentesMap = new HashMap<>();
        Map<String, Persona> personasFisicasExistentes = new HashMap<>();
        for (Colaborador colaborador : colaboradoresExistentes) {
            Persona persona = colaborador.getPersona_colaboradora();
            if (persona instanceof Persona_fisica personaFisicaExistente) { // solo mete a personas fisicas
                personasFisicasExistentes.put(personaFisicaExistente.getDocumento_identidad().getNumeroDocumento(), personaFisicaExistente);
            }
        }

        try {
            CSVReader reader = new CSVReader(new FileReader(archivoCsv));
            List<String[]> lineas = reader.readAll();
            // lee cada linea del csv
            for (String[] linea : lineas) {

                String tipoDoc = linea[0];
                // verifica longitud
                if (tipoDoc.length()>3) {
                    throw new IllegalArgumentException("El tipo de documento no puede tener más de 3 caracteres");
                }
                // paso tipoDoc a enum
                switch (tipoDoc){
                    case "LC"-> {
                        Tipo_documento tipoDocumento = Tipo_documento.LIBRETA_CIVICA;
                    }
                    case "LE"-> {
                        Tipo_documento tipoDocumento = Tipo_documento.LIBRETA_ENROLAMIENTO;
                    }
                    case "DNI"-> {
                        Tipo_documento tipoDocumento = Tipo_documento.DNI;
                    }
                }

                Integer numeroDocumento = Integer.parseInt(linea[1]);
                // paso numeroDocumento a string
                String numeroDocumentoString = Integer.toString(numeroDocumento).replace(".","");
                // verifica longitud
                if (numeroDocumentoString.length() > 10){
                    throw new IllegalArgumentException("El numero de documento no puede tener mas de 10 caracteres");
                }

                String nombre = linea[2];
                // verifica longitud
                if (nombre.length()>50) {
                    throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
                }

                String apellido = linea[3];
                // verifica longitud
                if (apellido.length()>50) {
                    throw new IllegalArgumentException("El apellido no puede tener más de 50 caracteres");
                }

                String mail = linea[4];

                // verifica longitud
                if (mail.length() > 50){
                    throw new IllegalArgumentException("El mail no puede tener más de 50 caracteres");
                }
                // verifica formato
                if (!mail.contains("@")){
                    throw new IllegalArgumentException("El mail no tiene un formato valido");
                }

                Date fechaColaboracion = new Date();
                // valida si el formato de fecha es correcto
                try {
                    fechaColaboracion = formatoFecha.parse(linea[5]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String formaColaboracion = linea[6];
                // verifica longitud
                if (formaColaboracion.length() > 22) {
                    throw new IllegalArgumentException("La forma de colaboracion no puede tener más de 22 caracteres");
                }

                Integer cantidad = Integer.parseInt(linea[7]);
                //verifico longitud no mayor a 7 caracteres
                if (cantidad > 9999999) {
                    throw new IllegalArgumentException("La cantidad no puede ser mayor a 9999999");
                }

                // creo los objetos con cada uno de los parametros recibidos
                // para crear a la persona necesito el objeto documento
                Documento_identidad documento = new Documento_identidad(numeroDocumentoString,tipoDocumento);
                // para crear al colaborador necesito a la persona
                Persona_fisica nuevaPersonaFisica = new Persona_fisica(nombre, apellido, documento);
                // creo un colaborador con la persona, todavia no tiene la contribucion
                Colaborador colaborador = new Colaborador(nuevaPersonaFisica);

                // aca verifico si ya existe un colaborador con esa persona, me fijo en el documento
                // si existe lo obtengo de colaboradores existentes, si no existe lo agrego a la lista de colaboradores del main
                if (!personasFisicasExistentes.containsKey(numeroDocumentoString)){
                    colaboradoresExistentes.add(colaborador);
                    personasFisicasExistentes.put(numeroDocumentoString, nuevaPersonaFisica);
                    colaboradoresExistentesMap.put(numeroDocumentoString, colaborador);
                } else {
                    colaborador = colaboradoresExistentesMap.get(numeroDocumentoString);
                }
                // POR AHORA TOMO QUE DONAR DINERO SEA UNA DONACION UNICA PORQ EN LA MIGRACION SE PIERDE LA FRECUENCIA
                // paso formaColaboracion a colaboracion
                switch (formaColaboracion) {
                    case "DINERO" -> {
                        Donacion_dinero donacionDinero = new Donacion_dinero(cantidad, Tipos_frecuencia.DONACION_UNICA, fechaColaboracion);
                        colaborador.agregarContribucion(donacionDinero);
                    }
                    case "REDISTRIBUCION_VIANDAS" -> {
                        Distribucion_viandas distribucionViandas = new Distribucion_viandas(cantidad, fechaColaboracion);
                        colaborador.agregarContribucion(distribucionViandas);
                    }
                    case "ENTREGA_TARJETAS" -> {
                        RegistrarPersonasSV entregaTarjetas = new RegistrarPersonasSV(cantidad, fechaColaboracion);
                        colaborador.agregarContribucion(entregaTarjetas);
                    }
                    case "DONACION_VIANDAS" -> {
                        // no seteo la cantidad de viandas porque deberia agregar la vianda a la lista de viandas pero seria una vianda vacia
                        Donacion_viandas donacionViandas = new Donacion_viandas(fechaColaboracion);
                        colaborador.agregarContribucion(donacionViandas);
                    }
                    default -> throw new Error("FORMA DE COLABORACION INVALIDA");
                }


            }
        } catch (IOException |CsvException e) {
            // si hubo un problema con la io como que no esta el archivo o en la lectura del csv existente
            e.printStackTrace();
        }

    }


}
