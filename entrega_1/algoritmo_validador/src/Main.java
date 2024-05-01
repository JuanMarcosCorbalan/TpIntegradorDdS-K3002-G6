import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        //String password;

        for(;;)
        {
            String password = Algoritmo.request_password();
            if(Algoritmo.is_password_too_short(password))
            {
                System.out.println("Password too short");
                continue;
            }
            else if(Algoritmo.is_password_in_top_1000(password)) {
                System.out.println("Password is in the top 10000");
                continue;
            }
            else{
                System.out.println("Password accepted");
            }
        }


    }

}