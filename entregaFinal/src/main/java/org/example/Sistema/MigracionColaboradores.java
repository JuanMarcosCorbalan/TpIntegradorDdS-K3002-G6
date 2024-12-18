package org.example.Sistema;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.RepositorioColaboradores;
import org.example.MigracionCsv.*;
import org.example.Persona.*;
import org.example.Formas_contribucion.*;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class MigracionColaboradores {

    String archivoCsv;
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    Tipo_documento tipoDocumento = null;
    Integer flagLongitud = 0;
    String numeroDocumentoString;
    LocalDate fechaColaboracion;
    Boolean primeraLinea = true;
    ObtencionLineasCsv obtencionLineasCsv = new ObtencionLineasCsv();
    TransformacionDatos transformacionDatos = new TransformacionDatos();
    ValidacionDatos validacionDatos = new ValidacionDatos();
    ExtraccionDatosLinea extraccionDatosLinea = new ExtraccionDatosLinea();
    CargaDatosCsv cargarDatosCsv = new CargaDatosCsv();
    RepositorioColaboradores colaboradoresExistentes;

    public MigracionColaboradores(String archivoCsv, RepositorioColaboradores colaboradores) {
        this.archivoCsv = archivoCsv;
        this.colaboradoresExistentes = colaboradores;
    }

    public List<DatosColaboracion> migrarCsv() {
        // mapeo para tener las personas en vez de colaboradores
        Map<String, Colaborador> colaboradoresExistentesMap = new HashMap<>();
        Map<String, Persona> personasFisicasExistentesMap = new HashMap<>();
        this.instanciacionHashMaps(colaboradoresExistentesMap, personasFisicasExistentesMap);
        List<DatosColaboracion> datosColaboraciones = new ArrayList<>();

        try {
            List<String[]> lineas = obtencionLineasCsv.leerCsv(archivoCsv);

            // lee cada linea del csv
            for (String[] linea : lineas) {
                DatosColaboracion datosColaboracion = new DatosColaboracion();

                extraccionDatosLinea.extraerDatosLinea(linea,datosColaboracion);
                numeroDocumentoString = transformacionDatos.numeroDocumentoString(datosColaboracion.getNumeroDocumento());
                tipoDocumento = transformacionDatos.stringToTipoDocumento(datosColaboracion.getTipoDoc());
                fechaColaboracion = transformacionDatos.stringToDate(datosColaboracion.getFechaColaboracionString(), formatoFecha);
                ValidacionDatos.validarLinea(linea, primeraLinea, datosColaboracion.getTipoDoc(), numeroDocumentoString, datosColaboracion.getNombre(), datosColaboracion.getApellido(), datosColaboracion.getMail(), datosColaboracion.getFormaColaboracion(), datosColaboracion.getCantidad());
                cargarDatosCsv.cargarDatos(colaboradoresExistentes.getColaboradores(),colaboradoresExistentesMap, personasFisicasExistentesMap, datosColaboracion.getNombre(), datosColaboracion.getApellido(), numeroDocumentoString, tipoDocumento, datosColaboracion.getMail(), datosColaboracion.getFormaColaboracion(), datosColaboracion.getCantidad(), fechaColaboracion);

                datosColaboraciones.add(datosColaboracion);
            }
        } catch (IOException |CsvException e) {
            // si hubo un problema con la io como que no esta el archivo o en la lectura del csv existente
            e.printStackTrace();
        }
        return datosColaboraciones;

    }

    public void instanciacionHashMaps(Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap){
        for (Colaborador colaborador : colaboradoresExistentes.getColaboradores()) {
            Persona persona = colaborador.getPersona_colaboradora();
            if (persona instanceof Persona_fisica personaFisicaExistente) { // solo mete a personas fisicas
                String numeroDocumento = personaFisicaExistente.getDocumento_identidad().getNumeroDocumento();
                personasFisicasExistentesMap.put(numeroDocumento, personaFisicaExistente);
                colaboradoresExistentesMap.put(numeroDocumento,colaborador);

            }
        }
    }


}
