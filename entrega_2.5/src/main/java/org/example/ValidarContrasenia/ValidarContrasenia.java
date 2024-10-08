package org.example.ValidarContrasenia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ValidarContrasenia {

    ArrayList<CondicionContrasenia> condiciones = new ArrayList<>();

    public ValidarContrasenia() throws IOException {
        LargoContrasenia largoContrasenia = new LargoContrasenia();
        condiciones.add(largoContrasenia);
        ContraseniaComun contraseniaComun = new ContraseniaComun();
        condiciones.add(contraseniaComun);
        TieneCaracterEspecial tieneCE = new TieneCaracterEspecial();
        condiciones.add(tieneCE);
        TieneMayuscula tieneMayuscula = new TieneMayuscula();
        condiciones.add(tieneMayuscula);
        TieneMinuscula tieneMinuscula = new TieneMinuscula();
        condiciones.add(tieneMinuscula);
        TieneNumero tieneNumero = new TieneNumero();
        condiciones.add(tieneNumero);
        // Agregar las condiciones aca
    }

    public boolean validar(String contrasenia) throws IOException {
        for(CondicionContrasenia condicion : condiciones)
        {
            if(!condicion.validar(contrasenia))
            {
                return false;
            }
        }
        return true;
    }

}
