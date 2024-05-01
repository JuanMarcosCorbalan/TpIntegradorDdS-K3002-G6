import java.io.*;
import java.util.Scanner;

public class Algoritmo {

    public static String request_password()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su contraseña: ");
        String password = input.nextLine();
        return password;
    }

    public static boolean is_password_too_short(String password)
    {
        return password.length() < 8;
    }

    public static boolean is_password_in_top_1000(String password) throws IOException {
        String nombreArchivo = "src/10-million-password-list-top-10000.txt";
        BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo));
        String lineaLeida;
        while((lineaLeida = entrada.readLine()) != null) {
            if(lineaLeida.equals(password)) {
                return true;
            }
        }
        return false;
    }

}