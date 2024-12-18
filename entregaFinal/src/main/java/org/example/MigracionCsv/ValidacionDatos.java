package org.example.MigracionCsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacionDatos {
    static Integer flagLongitud = 0;

    public static void validarLinea(String[] linea,Boolean primeraLinea,String tipoDoc ,String numeroDocumentoString, String nombre, String apellido, String mail, String formaColaboracion ,Integer cantidad){
        // tipoDocumento
        Integer longitudTipoDoc = tipoDoc.length();
        // se asegura si es la primer linea porque en esta lee un caracter inicial que no existe
        if(primeraLinea) {
            longitudTipoDoc = tipoDoc.length() - 1;
        }
        // verifica longitud
        flagLongitud = LargoDatoCsv.validarLongitud(longitudTipoDoc, 3);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El tipo de documento no puede tener mas de 3 caracteres");
        }

        // numeroDocumento
        // verifica longitud
        flagLongitud = LargoDatoCsv.validarLongitud(numeroDocumentoString.length(), 10);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El numero de documento no puede tener mas de 10 caracteres");
        }

        // nombreColaborador
        // verifica longitud
        flagLongitud = LargoDatoCsv.validarLongitud(nombre.length(), 50);
        if (flagLongitud == 0) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
        }

        // apellidoColaborador
        // verifica longitud
        flagLongitud = LargoDatoCsv.validarLongitud(apellido.length(), 50);
        if (flagLongitud == 0) {
            throw new IllegalArgumentException("El apellido no puede tener más de 50 caracteres");
        }

        // mailColaborador
        // verifica longitud
        flagLongitud = LargoDatoCsv.validarLongitud(mail.length(), 50);
        if (flagLongitud == 0){
            throw new IllegalArgumentException("El mail no puede tener más de 50 caracteres");
        }
        //todo verifica formato MEJORAR ESTO
        if (!mail.contains("@")){
            throw new IllegalArgumentException("El mail no tiene un formato valido");
        }

        // verifica longitud
        if (formaColaboracion.length() > 22) {
            throw new IllegalArgumentException("La forma de colaboracion no puede tener más de 22 caracteres");
        }

        //verifico longitud no mayor a 7 caracteres
        if (cantidad > 9999999) {
            throw new IllegalArgumentException("La cantidad no puede ser mayor a 9.999.999");
        }
    }
}
