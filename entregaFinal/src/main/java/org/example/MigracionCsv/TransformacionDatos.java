package org.example.MigracionCsv;

import org.example.Persona.Tipo_documento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransformacionDatos {
    Tipo_documento tipoDocumento = null;

    public Tipo_documento stringToTipoDocumento(String tipoDoc){
        switch (tipoDoc){
            case "LC"-> {
                return Tipo_documento.LIBRETA_CIVICA;
            }
            case "LE"-> {
                return Tipo_documento.LIBRETA_ENROLAMIENTO;
            }
            case "DNI"-> {
                return Tipo_documento.DNI;
            }
        }
        return null;
    }
    public String numeroDocumentoString(Integer numeroDocumento) {
        return Integer.toString(numeroDocumento).replace(".","");
    }

    public LocalDate stringToDate(String fechaString, DateTimeFormatter formatoFecha){
        // valida si el formato de fecha es correcto
        return LocalDate.parse(fechaString, formatoFecha);
    }

    public void transformarDatos(Integer numeroDocumento,String numeroDocumentoString,String tipoDocumento){
        this.stringToTipoDocumento(tipoDocumento);
        numeroDocumentoString = this.numeroDocumentoString(numeroDocumento);
    }

}
