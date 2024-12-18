package org.example.ValidarContrasenia;

import java.io.IOException;

public class TieneMayuscula extends CondicionContrasenia{

    @Override
    public boolean validar(String password) throws IOException {
        for(char c : password.toCharArray())
        {
            if(Character.isUpperCase(c))
            {
                return true;
            }
        }
        System.out.println(MensajeAviso.NO_UPPERCASE.obtenerAdvertencia());
        return false;
    }
}
