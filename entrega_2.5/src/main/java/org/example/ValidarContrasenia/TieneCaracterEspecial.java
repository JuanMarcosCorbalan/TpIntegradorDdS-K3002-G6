package org.example.ValidarContrasenia;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TieneCaracterEspecial extends CondicionContrasenia{

    @Override
    public boolean validar(String password) throws IOException {
        for(char c : password.toCharArray())
        {
            if(!Character.isLetterOrDigit(c))
            {
                return true;
            }
        }
        System.out.println(MensajeAviso.NO_SPECIAL_CHARACTER.obtenerAdvertencia());
        return false;
    }
}
