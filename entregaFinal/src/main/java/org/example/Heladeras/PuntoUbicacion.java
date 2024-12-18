package org.example.Heladeras;

import org.example.DAO.LocalidadDAO;
import org.example.Funcionalidades.BuscadorDireccion;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Utils.BDutils;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class PuntoUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String latitud;
    String longitud;
    String direccion;
    String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    Localidad localidad;

    @OneToMany(mappedBy = "puntoUbicacion",cascade = CascadeType.ALL)
    List<Heladera> heladeras = new ArrayList<Heladera>();

   // public PuntoUbicacion(String direccion) {
     //   this.direccion = direccion;
    //}

    public PuntoUbicacion(String latitud, String longitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public PuntoUbicacion(String latitud, String longitud, String direccion, String nombre, Localidad localidad) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.direccion = direccion;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public PuntoUbicacion(String coordenadasYDireccion) {
        // Dividir el string recibido por las comas
        String[] partes = coordenadasYDireccion.split(",");

        // Verificar que el String tiene tres partes (latitud, longitud, direccion)
        if (partes.length == 3) {
            try {
                // Convertir latitud y longitud a double
                this.latitud = partes[0].trim();
                this.longitud = partes[1].trim();
                // Asignar la dirección
                this.direccion = partes[2].trim();
            } catch (NumberFormatException e) {
                // Si no se puede convertir latitud o longitud, establecer valores por defecto o lanzar excepción
                //System.out.println("Error al parsear las coordenadas.");
                this.latitud = "0";
                this.longitud = "0";
                this.direccion = "Desconocida";
            }
        } else {
            //System.out.println("Formato incorrecto. Se esperaba latitud,longitud,direccion.");
            this.latitud = "0";
            this.longitud = "0";
            this.direccion = "Desconocida";
        }
    }



    public PuntoUbicacion() {

    }

    public void aniadirHeladera(Heladera heladera){
        heladeras.add(heladera);
    }
    public void quitarHeladera(Heladera heladera){
        heladeras.remove(heladera);
    }

    public String getLatitud() {return latitud;}
    public String getLongitud() {return longitud;}
    public String getDireccion() {return direccion;}
    public String getNombre(){return nombre;}


    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombreHeladera) {
        this.nombre = nombreHeladera;
    }

    public void completarDireccion(){
        BuscadorDireccion buscadorDireccion = new BuscadorDireccion();
        String direccionCompleta = buscadorDireccion.buscarDireccion(latitud, longitud);
        this.direccion = obtenerDireccion(direccionCompleta);
    }

    public void completarUbicacion(EntityManager em){
        LocalidadDAO ldao = new LocalidadDAO(em);
        BuscadorDireccion buscadorDireccion = new BuscadorDireccion();
        String direccionCompleta = buscadorDireccion.buscarDireccion(latitud, longitud);
        String ciudad = obtenerCiudad(direccionCompleta);
        String pais = obtenerPais(direccionCompleta);
        String localidad = buscadorDireccion.buscarBarrio(direccionCompleta);

        this.localidad = ldao.findOrCreate(localidad, ciudad, pais);

    }

    public static String obtenerDireccion(String direccionCompleta) {
        // Divide la cadena en partes usando la coma
        String[] partes = direccionCompleta.split(",");

        // Retorna la primera parte como dirección (quitando espacios extra)
        return partes[0].trim();
    }

    public static String obtenerCiudad(String direccionCompleta) {
        // Divide la cadena en partes usando la coma
        String[] partes = direccionCompleta.split(",");

        if (partes.length >= 2) {
            // Segunda parte: elimina el código postal si existe
            String ciudad = partes[1].replaceAll("\\bC\\d{4}\\w{0,3}\\b", "").trim();

            // Elimina dobles espacios si quedaron
            return ciudad.replaceAll("\\s{2,}", " ");
        }
        return "";
    }

    public static String obtenerPais(String direccionCompleta) {
        // Divide la cadena en partes usando la coma
        String[] partes = direccionCompleta.split(",");

        // Retorna la última parte como país
        return partes[partes.length - 1].trim();
    }


    public Localidad getLocalidad() {
        return localidad;
    }
}
