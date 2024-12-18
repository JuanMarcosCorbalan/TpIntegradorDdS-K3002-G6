package org.example.MigracionCsv;

public class LargoDatoCsv {
    public static Integer validarLongitud(int longitud_a_validar, int longitud_maxima) {
        if (longitud_a_validar > longitud_maxima) {
            return 0;
        }
        return 1;
    }
}
