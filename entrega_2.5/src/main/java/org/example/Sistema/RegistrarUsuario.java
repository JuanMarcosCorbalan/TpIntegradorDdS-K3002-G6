package org.example.Sistema;

import org.example.ValidarContrasenia.ValidarContrasenia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegistrarUsuario {

    static Map<String,String> usuarios_contrasenias = new HashMap<String,String>();
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar nombre de usuario:");
        String nombre = scanner.nextLine();
        System.out.print("Ingresar contraseña: ");
        String contrasenia = scanner.nextLine();

        comprobar_registrar_usuario(nombre,contrasenia);

        //FALTA VALIDAR QUE NO HAYA DOS USUARIOS IGUALES, NO DEJAR REGISTRAR SI YA HAY UNO CON ESE NOMBRE
        //HACER AMBAS VALIDACIONES EN UN SOLO METODO LLAMADO VALIDAR REGISTRO
        //CAMBIARLE EL NOMBRE A LA FUNCION INTEGRADORA, ALGO MAS DECLARATIVO


    }

    public static void comprobar_registrar_usuario(String nombre,String contrasenia) throws IOException {
        if(validar_usuario(nombre,contrasenia))
        {
            System.out.print("\nUSUARIO REGISTRADO CON EXITO\n");
            registrar_usuario(nombre,contrasenia);
        }else
        {
            System.out.print("\nCONTRASEÑA INVALIDA, USUARIO RECHAZADO\n");
        }
    }



    public static void registrar_usuario(String nombre, String contrasenia) {
        usuarios_contrasenias.put(nombre,contrasenia);
    }

    public static boolean validar_usuario(String usuario, String contrasenia) throws IOException {
        ValidarContrasenia validarContrasenia = new ValidarContrasenia();
        return validarContrasenia.validar(contrasenia);
    }

}
