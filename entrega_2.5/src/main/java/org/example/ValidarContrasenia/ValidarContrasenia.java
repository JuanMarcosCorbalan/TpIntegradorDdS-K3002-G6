package org.example.ValidarContrasenia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ValidarContrasenia {

    ArrayList<CondicionContrasenia> condiciones = new ArrayList<>();

    public ValidarContrasenia()
    {
        LargoContrasenia largoContrasenia = new LargoContrasenia();
        condiciones.add(largoContrasenia);
        ContraseniaComun contraseniaComun = new ContraseniaComun();
        condiciones.add(contraseniaComun);
        // Agregar las condiciones aca
    }

    public boolean validar(String contrasenia) throws IOException {
        for(CondicionContrasenia condicion : condiciones)
        {
            if(condicion.validar(contrasenia) != -1)
            {
                return false;
            }
        }
        return true;
    }

}
