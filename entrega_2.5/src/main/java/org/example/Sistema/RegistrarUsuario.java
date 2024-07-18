package org.example.Sistema;

import org.example.ValidarContrasenia.ValidarContrasenia;

import java.io.IOException;
import java.util.Scanner;

public class RegistrarUsuario {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar nombre de usuario:");
        String nombre = scanner.nextLine();
        System.out.print("Ingresar contraseña: ");
        String contrasenia = scanner.nextLine();

        ValidarContrasenia validarContrasenia = new ValidarContrasenia();
        if(!validarContrasenia.validar(contrasenia))
            System.out.print("\nCONTRASEÑA INVALIDA, USUARIO RECHAZADO\n");
        else System.out.print("\nUSUARIO REGISTRADO CON EXITO\n");


        //guardar usuario y contraseña por ahora
    }

}
