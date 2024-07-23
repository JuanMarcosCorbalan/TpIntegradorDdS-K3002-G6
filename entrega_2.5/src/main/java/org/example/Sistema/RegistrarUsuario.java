package org.example.Sistema;

import org.example.ValidarContrasenia.ValidarContrasenia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class RegistrarUsuario {

    static Map<String,String> usuarios_contrasenias = new HashMap<String,String>();
    public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingresar nombre de usuario:");
            String nombre = scanner.nextLine();
            System.out.print("Ingresar contraseña: ");
            String contrasenia = scanner.nextLine();

            comprobar_registrar_usuario(nombre, contrasenia);


    }

    public static void comprobar_registrar_usuario(String nombre,String contrasenia) throws IOException {
        if(validar_registro(nombre,contrasenia))
        {
            System.out.print("\nUSUARIO REGISTRADO CON EXITO\n");
            registrar_usuario(nombre,contrasenia);
        }else
        {
            System.out.print("\nUSUARIO RECHAZADO\n");
        }
    }


    public static boolean validar_registro(String nombre, String contrasenia) throws IOException {
        return validar_nombre(nombre) && validar_contrasenia(contrasenia);
    }

    public static void registrar_usuario(String nombre, String contrasenia) {
        usuarios_contrasenias.put(nombre,contrasenia);
    }

    public static boolean validar_contrasenia(String contrasenia) throws IOException {
        ValidarContrasenia validarContrasenia = new ValidarContrasenia();
        return validarContrasenia.validar(contrasenia);
    }
    public static boolean validar_nombre(String nombre) throws IOException {
        for(String key : usuarios_contrasenias.keySet())
        {
            if(Objects.equals(nombre, key)) {
                System.out.print("\n YA EXISTE UN USUARIO REGISTRADO CON ESE NOMBRE \n");
                return false;
            }
        }
        return true;
    }
}
