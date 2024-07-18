package org.example.ValidarContrasenia;

public class LargoContrasenia extends CondicionContrasenia {

    @Override
    public boolean validar(String password)
    {
        if(password.length() < 8)
        {
            System.out.print("LA CONTRASEÑA TIENE MENOS DE 8 CARACTERES");
            return false;
        }
        else return true;
    }
}
