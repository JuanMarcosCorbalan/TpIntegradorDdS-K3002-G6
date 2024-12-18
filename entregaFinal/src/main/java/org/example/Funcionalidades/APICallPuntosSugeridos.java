package org.example.Funcionalidades;

import org.example.Interfaz.APIMapa;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;

public class APICallPuntosSugeridos {
    Punto puntoABuscar;
    Integer radio;


    public String getPuntosSugeridos() {

        try{
            URL url = new URL("https://2876ef25-6c10-495b-9288-177a2ee7badd.mock.pstmn.io/api/puntosRecomendados/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();
            String json_recibido;
            int response_code = conn.getResponseCode();
            if(response_code!=200){
                //Lanzamos excepcion en tiempo de ejecucion
                throw new RuntimeException("Ocurrio un error: "+response_code);
            }else {
                StringBuilder information_string = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext())
                {
                    information_string.append(scanner.nextLine());
                }
                scanner.close();
                json_recibido = information_string.toString();

            }

            conn.disconnect();
            return json_recibido;

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

}
