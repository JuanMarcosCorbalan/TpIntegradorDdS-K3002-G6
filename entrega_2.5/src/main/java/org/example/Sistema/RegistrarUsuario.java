package org.example.Sistema;

import java.util.Scanner;

public class RegistrarUsuario {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar nombre de usuario:");
        String nombre = scanner.nextLine();
        System.out.print("Ingresar contraseña: ");
        String contrasenia = scanner.nextLine();

        //validarContrasenia.validar(contrasenia);

        //guardar usuario y contraseña por ahora
    }

}
