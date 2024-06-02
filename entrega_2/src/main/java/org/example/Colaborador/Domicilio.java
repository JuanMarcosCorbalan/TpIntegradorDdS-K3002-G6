package org.example.Colaborador;

public class Domicilio {
    String latitud;
    String longitud;
    Ciudad ciudad;
    String direccion;
    Pais Pais;

    public Domicilio (String latitud, String longitud, String direccion, Ciudad ciudad, Pais Pais) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.Pais = Pais;
    }
}
