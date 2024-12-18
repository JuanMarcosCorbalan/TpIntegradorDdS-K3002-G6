package org.example.MigracionCsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtraccionDatosLinea {

    public void extraerDatosLinea(String[] linea, DatosColaboracion datos){
        datos.setTipoDoc(linea[0]);
        datos.setNumeroDocumento(Integer.parseInt(linea[1]));
        datos.setNombre(linea[2]);
        datos.setApellido(linea[3]);
        datos.setMail(linea[4]);
        datos.setFechaColaboracionString(linea[5]);
        datos.setFormaColaboracion(linea[6]);
        datos.setCantidad(Integer.parseInt(linea[7]));
        }
}
