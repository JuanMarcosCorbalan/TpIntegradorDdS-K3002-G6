package org.example.ValidarContrasenia;

import static org.example.ValidarContrasenia.condiciones.CONTRASENIA_MENOR_OCHO_CARACTERES;

public class LargoContrasenia extends CondicionContrasenia {

    @Override
    public int validar(String password)
    {
        if(password.length() < 8)
        {
            return CONTRASENIA_MENOR_OCHO_CARACTERES.ordinal();
        }
        else
        {
            return -1;
        }
    }
}
