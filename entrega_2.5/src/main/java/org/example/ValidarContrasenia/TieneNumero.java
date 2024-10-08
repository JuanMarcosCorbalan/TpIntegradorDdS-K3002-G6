package org.example.ValidarContrasenia;

import java.io.IOException;

public class TieneNumero extends CondicionContrasenia{

    @Override
    public boolean validar(String password) throws IOException {
        for(char c : password.toCharArray())
        {
            if(Character.isDigit(c))
            {
                return true;
            }
        }
        System.out.println(MensajeAviso.NO_DIGIT.obtenerAdvertencia());
        return false;
    }
}
