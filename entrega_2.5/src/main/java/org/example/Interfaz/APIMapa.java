package org.example.Interfaz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import java.util.List;

public class APIMapa {


    public String generar_url(String apikey,List<String> marcadores)
    {
        String url_inicial = "https://maps.googleapis.com/maps/api/staticmap?center=Caballito,Ciudad+Autónoma+de+Buenos+Aires,Argentina&format=jpg&zoom=12&size=800x800&scale=2&maptype=roadmap";
        String antes_key = "&key=";
        String url_completa = url_inicial;
        int cantidad_marcadores = marcadores.size();

        for (String marcador : marcadores) {
            url_completa += marcador;
        }

        url_completa += antes_key;
        url_completa += apikey;

        return url_completa;
    }

    public Image getMapa(String url_completa){

        try{
            //Creamos una url
            URL url = new URL(url_completa);
            //Con la url abrimos la conexion
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Establecemos que el metodo de conexion sea GET
            conn.setRequestMethod("GET");
            //Nos conectamos
            conn.connect();
            //Brooklyn+Bridge,New+York,NY
            ImageIcon imageIcon;
            Image image;
            //Codigo respuesta = 200 correcto, 500 error servidor, 404, etc
            int response_code = conn.getResponseCode();
            if(response_code!=200){
                //Lanzamos excepcion en tiempo de ejecucion
                throw new RuntimeException("Ocurrio un error: "+response_code);
            }else {
                InputStream inputStream = conn.getInputStream();
                image = ImageIO.read(inputStream);
                //imageIcon = new ImageIcon(image);
                inputStream.close();
            }
            conn.disconnect();
            return image;


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
