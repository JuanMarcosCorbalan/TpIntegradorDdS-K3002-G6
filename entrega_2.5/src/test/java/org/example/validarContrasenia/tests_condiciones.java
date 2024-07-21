package org.example.validarContrasenia;
import static org.example.ValidarContrasenia.condiciones.CONTRASENIA_EN_EL_TOP;
import static org.example.ValidarContrasenia.condiciones.CONTRASENIA_MENOR_OCHO_CARACTERES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.ValidarContrasenia.ContraseniaComun;
import org.example.ValidarContrasenia.LargoContrasenia;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class tests_condiciones {
    @Test
    void contrasenia_menor_ocho_caracteres() {
        LargoContrasenia lc = new LargoContrasenia();
        String contraseniaPrueba = "hola co";
        int resultado = lc.validar(contraseniaPrueba);
        assertEquals(CONTRASENIA_MENOR_OCHO_CARACTERES.ordinal(),resultado,"La contrasenia tiene menos de 8 caracteres");
    }

    @Test
    void contrasenia_mayor_igual_ocho_caracteres() {
        LargoContrasenia lc = new LargoContrasenia();
        String contraseniaPrueba = "holacomo";
        int resultado = lc.validar(contraseniaPrueba);
        assertEquals(-1,resultado,"La contrasenia es mayor o igual a ocho caracteres");
    }

    @Test
    void contrasenia_en_top_10000() throws IOException {
        ContraseniaComun contraseniaEnTop = new ContraseniaComun();
        String contraseniaPrueba = "123456789";
        int resultado = contraseniaEnTop.validar(contraseniaPrueba);
        assertEquals(CONTRASENIA_EN_EL_TOP.ordinal(),resultado,"La contrasenia esta presente en top 10000");
    }

    @Test
    void contrasenia_ausente_en_top_10000() throws IOException {
        ContraseniaComun contraseniaEnTop = new ContraseniaComun();
        String contraseniaPrueba = "Rubi2487";
        int resultado = contraseniaEnTop.validar(contraseniaPrueba);
        assertEquals(-1,resultado,"La contrasenia no esta en el top 10000");
    }



}
