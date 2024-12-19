package org.example.ValidarContrasenia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestsValidacionContrasenia {
    /*
    @Test
    void contrasenia_menor_ocho_caracteres() throws IOException {
        LargoContrasenia lc = new LargoContrasenia();
        String contraseniaPrueba = "hola co";
        boolean resultado = lc.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia tiene menos de 8 caracteres");
    }

    @Test
    void contrasenia_mayor_igual_ocho_caracteres() throws IOException {
        LargoContrasenia lc = new LargoContrasenia();
        String contraseniaPrueba = "holacomo";
        boolean resultado = lc.validar(contraseniaPrueba);
        assertTrue(resultado, "La contrasenia es mayor o igual a ocho caracteres");
    }

    @Test
    void contrasenia_en_top_10000() throws IOException {
        ContraseniaComun contraseniaEnTop = new ContraseniaComun();
        String contraseniaPrueba = "123456789";
        boolean resultado = contraseniaEnTop.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia esta presente en top 10000");
    }

    @Test
    void contrasenia_ausente_en_top_10000() throws IOException {
        ContraseniaComun contraseniaEnTop = new ContraseniaComun();
        String contraseniaPrueba = "Rubi2487";
        boolean resultado = contraseniaEnTop.validar(contraseniaPrueba);
        assertTrue(resultado, "La contrasenia no esta en el top 10000");
    }

    @Test
    void contrasenia_tiene_mayuscula() throws IOException {
        TieneMayuscula contraseniaMayus = new TieneMayuscula();
        String contraseniaPrueba = "Robert";
        boolean resultado = contraseniaMayus.validar(contraseniaPrueba);
        assertTrue(resultado, "La contrasenia tiene mayuscula");
    }

    @Test
    void contrasenia_no_tiene_mayuscula() throws IOException {
        TieneMayuscula contraseniaMayus = new TieneMayuscula();
        String contraseniaPrueba = "robert";
        boolean resultado = contraseniaMayus.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia no tiene mayuscula");
    }

    @Test
    void contrasenia_tiene_minuscula() throws IOException {
        TieneMinuscula contraseniaMinus = new TieneMinuscula();
        String contraseniaPrueba = "ROBERt";
        boolean resultado = contraseniaMinus.validar(contraseniaPrueba);
        assertTrue(resultado, "La contrasenia tiene minuscula");
    }

    @Test
    void contrasenia_no_tiene_minuscula() throws IOException {
        TieneMinuscula contraseniaMinus = new TieneMinuscula();
        String contraseniaPrueba = "ROBERT";
        boolean resultado = contraseniaMinus.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia no tiene minuscula");
    }

    @Test
    void contrasenia_tiene_numero() throws IOException {
        TieneNumero contraseniaNumero = new TieneNumero();
        String contraseniaPrueba = "Aires2";
        boolean resultado = contraseniaNumero.validar(contraseniaPrueba);
        assertTrue(resultado, "La contrasenia tiene al menos un numero");
    }

    @Test
    void contrasenia_no_tiene_numero() throws IOException {
        TieneNumero contraseniaNumero = new TieneNumero();
        String contraseniaPrueba = "Airess";
        boolean resultado = contraseniaNumero.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia no tiene al menos un numero");
    }

    @Test
    void contrasenia_tiene_caracter_especial() throws IOException {
        TieneCaracterEspecial contraseniaEsp = new TieneCaracterEspecial();
        String contraseniaPrueba = "Cordob@";
        String contraseniaPrueba2 = "Cordob ";
        boolean resultado = contraseniaEsp.validar(contraseniaPrueba);
        boolean resultado2 = contraseniaEsp.validar(contraseniaPrueba2);
        assertTrue(resultado, "La contrasenia tiene un caracter especial");
        assertTrue(resultado2, "La contrasenia tiene un caracter especial");
    }

    @Test
    void contrasenia_no_tiene_caracter_especial() throws IOException {
        TieneCaracterEspecial contraseniaEsp = new TieneCaracterEspecial();
        String contraseniaPrueba = "Cordoba123";
        boolean resultado = contraseniaEsp.validar(contraseniaPrueba);
        assertFalse(resultado, "La contrasenia no tiene un caracter especial");
    }
*/


}
