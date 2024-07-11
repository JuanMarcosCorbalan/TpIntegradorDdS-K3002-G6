package org.example.Sistema;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.Funcionalidades.EnvioMail;
import org.example.Persona.*;
import org.example.Formas_contribucion.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MigracionColaboradores {

    String archivoCsv = "csvs/csvColaboradores.csv";
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    Tipo_documento tipoDocumento = null;
    Integer flagLongitud = 0;
    String numeroDocumentoString;
    String nombre;
    String apellido;
    String formaColaboracion;
    String mail;
    Integer cantidad;
    Date fechaColaboracion;
    Boolean primeraLinea = true;

    public void migrarCsv(List<Colaborador> colaboradoresExistentes) {

        // mapeo para tener las personas en vez de colaboradores
        Map<String, Colaborador> colaboradoresExistentesMap = new HashMap<>();
        Map<String, Persona> personasFisicasExistentesMap = new HashMap<>();
        this.instanciacionHashMaps(colaboradoresExistentes,colaboradoresExistentesMap, personasFisicasExistentesMap);

        try {
            List<String[]> lineas = this.leerCsv();

            // lee cada linea del csv
            for (String[] linea : lineas) {

                this.validarLinea(linea);

                this.crearObjetos(colaboradoresExistentes,colaboradoresExistentesMap, personasFisicasExistentesMap);

            }
        } catch (IOException |CsvException e) {
            // si hubo un problema con la io como que no esta el archivo o en la lectura del csv existente
            e.printStackTrace();
        }

    }

    public List<String[]> leerCsv() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(archivoCsv));
        List<String[]> lineas = reader.readAll();
        return lineas;
    }

    public void validarLinea(String[] linea){
        // tipoDocumento
        String tipoDoc = linea[0];
        // verifica longitud
        Integer longitudTipoDoc = tipoDoc.length();
        if(primeraLinea) {
            longitudTipoDoc = tipoDoc.length() - 1;
        }
        flagLongitud = this.validarLongitud(longitudTipoDoc, 3);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El tipo de documento no puede tener mas de 3 caracteres");
        }

        // paso tipoDoc a enum
        this.stringToTipoDocumento(tipoDoc);

        // numeroDocumento
        Integer numeroDocumento = Integer.parseInt(linea[1]);
        // paso numeroDocumento a string
        numeroDocumentoString = Integer.toString(numeroDocumento).replace(".","");
        // verifica longitud
        flagLongitud = this.validarLongitud(numeroDocumentoString.length(), 10);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El numero de documento no puede tener mas de 10 caracteres");
        }
        this.validarLongitud(numeroDocumentoString.length(), 10);

        // nombreColaborador
        nombre = linea[2];
        // verifica longitud
        flagLongitud = this.validarLongitud(nombre.length(), 50);
        if (flagLongitud == 0) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
        }

        apellido = linea[3];
        // verifica longitud
        flagLongitud = this.validarLongitud(apellido.length(), 50);
        if (flagLongitud == 0) {
            throw new IllegalArgumentException("El apellido no puede tener más de 50 caracteres");
        }

        String mail = linea[4];

        // verifica longitud
        flagLongitud = this.validarLongitud(mail.length(), 50);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El mail no puede tener más de 50 caracteres");
        }
        // verifica formato
        if (!mail.contains("@")){
            throw new IllegalArgumentException("El mail no tiene un formato valido");
        }

        fechaColaboracion = new Date();
        // valida si el formato de fecha es correcto
        try {
            fechaColaboracion = formatoFecha.parse(linea[5]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        formaColaboracion = linea[6];
        // verifica longitud
        if (formaColaboracion.length() > 22) {
            throw new IllegalArgumentException("La forma de colaboracion no puede tener más de 22 caracteres");
        }

        cantidad = Integer.parseInt(linea[7]);
        //verifico longitud no mayor a 7 caracteres
        if (cantidad > 9999999) {
            throw new IllegalArgumentException("La cantidad no puede ser mayor a 9999999");
        }
    }

    public Integer validarLongitud(int longitud_a_validar, int longitud_maxima){
        if (longitud_a_validar>longitud_maxima) {
            return 0;
        }
        return 1;
    }

    public void stringToTipoDocumento(String tipoDoc){
        switch (tipoDoc){
            case "LC"-> {
                tipoDocumento = Tipo_documento.LIBRETA_CIVICA;
            }
            case "LE"-> {
                tipoDocumento = Tipo_documento.LIBRETA_ENROLAMIENTO;
            }
            case "DNI"-> {
                tipoDocumento = Tipo_documento.DNI;
            }
        }
    }

    public Documento_identidad crearDocumento(String numeroDocumentoString, Tipo_documento tipoDocumento){
        return new Documento_identidad(numeroDocumentoString,tipoDocumento);
    }

    public Persona_fisica crearPersonaFisica(String nombre, String apellido, Documento_identidad documento){
        return new Persona_fisica(nombre, apellido, documento);
    }

    public Colaborador crearColaborador(Persona_fisica nuevaPersonaFisica) {
        return new Colaborador(nuevaPersonaFisica);
    }

    public Boolean validarExistencia(Map<String, Persona> personasFisicasExistentesMap){
        return personasFisicasExistentesMap.containsKey(numeroDocumentoString);

    }

    public Colaborador obtenerColaborador(List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap) throws IOException {
        Boolean existeColaborador = this.validarExistencia(personasFisicasExistentesMap);
        if (!existeColaborador){
            // creo los objetos con cada uno de los parametros recibidos
            // para crear a la persona necesito el objeto documento
            Documento_identidad documento = this.crearDocumento(numeroDocumentoString, tipoDocumento);

            // para crear al colaborador necesito a la persona
            Persona_fisica nuevaPersonaFisica = this.crearPersonaFisica(nombre, apellido, documento);
            // creo un colaborador con la persona, todavia no tiene la contribucion
            Colaborador colaborador = this.crearColaborador(nuevaPersonaFisica);

            this.agregarColaboradorNuevoAExistentes(colaborador, nuevaPersonaFisica, colaboradoresExistentes, colaboradoresExistentesMap, personasFisicasExistentesMap);
            Email emailPersona = new Email(mail);
            EnvioMail.enviarEmail(emailPersona);
            return colaborador;
        } else {
            return colaboradoresExistentesMap.get(numeroDocumentoString);
        }

    }

    public void agregarColaboradorNuevoAExistentes(Colaborador nuevoColaborador, Persona_fisica nuevaPersonaFisica, List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap){
        colaboradoresExistentes.add(nuevoColaborador);
        personasFisicasExistentesMap.put(numeroDocumentoString, nuevaPersonaFisica);
        colaboradoresExistentesMap.put(numeroDocumentoString, nuevoColaborador);
    }

    public void identificarYCrearColaboracion(Colaborador colaborador){
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

    public void crearObjetos(List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap) throws IOException {

        // aca verifico si ya existe un colaborador con esa persona, me fijo en el documento
        // si existe lo obtengo de colaboradores existentes, si no existe lo creo y lo agrego a la lista de colaboradores del main
        Colaborador colaborador = this.obtenerColaborador(colaboradoresExistentes, colaboradoresExistentesMap, personasFisicasExistentesMap);
        // POR AHORA TOMO QUE DONAR DINERO SEA UNA DONACION UNICA PORQ EN LA MIGRACION SE PIERDE LA FRECUENCIA
        // paso formaColaboracion a colaboracion
        this.identificarYCrearColaboracion(colaborador);
    }

    public void instanciacionHashMaps(List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap){
        for (Colaborador colaborador : colaboradoresExistentes) {
            Persona persona = colaborador.getPersona_colaboradora();
            if (persona instanceof Persona_fisica personaFisicaExistente) { // solo mete a personas fisicas
                String numeroDocumento = personaFisicaExistente.getDocumento_identidad().getNumeroDocumento();
                personasFisicasExistentesMap.put(numeroDocumento, personaFisicaExistente);
                colaboradoresExistentesMap.put(numeroDocumento,colaborador);

            }
        }
    }


}
