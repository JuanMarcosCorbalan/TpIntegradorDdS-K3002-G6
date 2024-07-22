package org.example.ValidarContrasenia;


import java.io.IOException;

public class LargoContrasenia extends CondicionContrasenia {

    @Override
    public boolean validar(String password) throws IOException {
        if(password.length() >= 8)
        {
            return true;
        }
        else
        {
            System.out.println(MensajeAviso.LESS_THAN_EIGHT.obtenerAdvertencia());
            return false;
        }
    }
}
