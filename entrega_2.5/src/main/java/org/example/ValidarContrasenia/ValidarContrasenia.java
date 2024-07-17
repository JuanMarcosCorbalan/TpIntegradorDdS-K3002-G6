package org.example.ValidarContrasenia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ValidarContrasenia {

    ArrayList<CondicionContrasenia> condiciones = new ArrayList<>();

    public void setearCondiciones()
    {
        LargoContrasenia largoContrasenia = new LargoContrasenia();
        condiciones.add(largoContrasenia);
        ContraseniaComun contraseniaComun = new ContraseniaComun();
        condiciones.add(contraseniaComun);

    }

    public boolean validar(String contrasenia)
    {
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
