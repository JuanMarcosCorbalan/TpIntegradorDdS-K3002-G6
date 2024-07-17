package org.example.ValidarContrasenia;

public class LargoContrasenia extends CondicionContrasenia {

    @Override
    public boolean validar(String password)
    {
        return password.length() < 8;
    }
}
