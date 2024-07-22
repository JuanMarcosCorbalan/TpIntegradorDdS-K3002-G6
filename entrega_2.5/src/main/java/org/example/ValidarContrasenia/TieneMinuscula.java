package org.example.ValidarContrasenia;

import java.io.IOException;

public class TieneMinuscula extends CondicionContrasenia{

    @Override
    public boolean validar(String password) throws IOException{
        for(char c : password.toCharArray())
        {
            if(Character.isLowerCase(c))
            {
                return true;
            }
        }
        System.out.println(MensajeAviso.NO_LOWERCASE.obtenerAdvertencia());
        return false;
    }
}
