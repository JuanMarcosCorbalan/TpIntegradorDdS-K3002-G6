package org.example.Persona;

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

    public Domicilio () {}

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setPais(Pais Pais) {
        this.Pais = Pais;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
