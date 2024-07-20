package org.example.Sistema;

import org.example.Colaborador.Colaborador;
import org.example.MigracionCsv.*;
import org.example.Persona.*;
import org.example.Formas_contribucion.*;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

public class MigracionColaboradores {

    String archivoCsv = "csvs/csvColaboradores.csv";
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    Tipo_documento tipoDocumento = null;
    //String tipoDocumentoString;
    Integer flagLongitud = 0;
    //Integer numeroDocumento;
    String numeroDocumentoString;
    //String nombre;
    //String apellido;
    //String formaColaboracion;
    //String mail;
    //Integer cantidad;
    //String fechaColaboracionString;
    Date fechaColaboracion;
    Boolean primeraLinea = true;
    ObtencionLineasCsv obtencionLineasCsv = new ObtencionLineasCsv();
    TransformacionDatos transformacionDatos = new TransformacionDatos();
    ValidacionDatos validacionDatos = new ValidacionDatos();
    ExtraccionDatosLinea extraccionDatosLinea = new ExtraccionDatosLinea();
    CargaDatosCsv cargarDatosCsv = new CargaDatosCsv();
    DatosColaboracion datosColaboracion = new DatosColaboracion();

    public void migrarCsv(List<Colaborador> colaboradoresExistentes) {
        // mapeo para tener las personas en vez de colaboradores
        Map<String, Colaborador> colaboradoresExistentesMap = new HashMap<>();
        Map<String, Persona> personasFisicasExistentesMap = new HashMap<>();
        this.instanciacionHashMaps(colaboradoresExistentes,colaboradoresExistentesMap, personasFisicasExistentesMap);

        try {
            List<String[]> lineas = obtencionLineasCsv.leerCsv(archivoCsv);

            // lee cada linea del csv
            for (String[] linea : lineas) {

                extraccionDatosLinea.extraerDatosLinea(linea,datosColaboracion);
                numeroDocumentoString = transformacionDatos.numeroDocumentoString(datosColaboracion.getNumeroDocumento());
                tipoDocumento = transformacionDatos.stringToTipoDocumento(datosColaboracion.getTipoDoc());
                fechaColaboracion = transformacionDatos.stringToDate(datosColaboracion.getFechaColaboracionString(), formatoFecha);
                ValidacionDatos.validarLinea(linea, primeraLinea, datosColaboracion.getTipoDoc(), numeroDocumentoString, datosColaboracion.getNombre(), datosColaboracion.getApellido(), datosColaboracion.getMail(), datosColaboracion.getFormaColaboracion(), datosColaboracion.getCantidad());
                cargarDatosCsv.cargarDatos(colaboradoresExistentes,colaboradoresExistentesMap, personasFisicasExistentesMap, datosColaboracion.getNombre(), datosColaboracion.getApellido(), numeroDocumentoString, tipoDocumento, datosColaboracion.getMail(), datosColaboracion.getFormaColaboracion(), datosColaboracion.getCantidad(), fechaColaboracion);

            }
        } catch (IOException |CsvException e) {
            // si hubo un problema con la io como que no esta el archivo o en la lectura del csv existente
            e.printStackTrace();
        }

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
