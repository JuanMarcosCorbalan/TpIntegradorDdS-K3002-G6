package org.example.Interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayImagen extends JFrame {



    public DisplayImagen(Image image) {
        // Título de la ventana
        setTitle("MapaHeladeras");

        // Configuración del tamaño de la ventana
        setSize(800, 800);

        // Finaliza la ejecución del programa al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centro la ventana en la pantalla
        setLocationRelativeTo(null);

        // Crea un panel para agregar la imagen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen en el panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Agrega el panel a la ventana
        add(panel);
    }

    //MAIN PARA PRUEBA VISUALIZACION MAPA
    public static void main (String[] args) {
        //CREO LISTA MARCADORES
        List<String> marcadores = new ArrayList<String>();
        //CREO UNA NUEVA URL CON 3 MARCADORES Y HAGO EL GET CON DICHA URL
        APIMapa mapa = new APIMapa();
        Mapa funciones_mapa = new Mapa();
        //ACA SE PUEDE ANIADIR MAS MARCOS, LIMITADO A CABA LA VISUALIZACION DEL MAPA ESTATICO
        funciones_mapa.aniadir_marcador("-34.607961","-58.435581",marcadores);
        funciones_mapa.aniadir_marcador("-34.668109","-58.464479",marcadores);
        funciones_mapa.aniadir_marcador("-34.629227","-58.392630",marcadores);
        funciones_mapa.aniadir_marcador("-34.587812","-58.455129",marcadores);
        String url = mapa.generar_url("AIzaSyBbk64dFcJuFyrppzuM8nw4OtitgvPXzIw",marcadores);
        Image image = mapa.getMapa(url);

        DisplayImagen frame = new DisplayImagen(image);
        frame.setVisible(true);

        }

    }

