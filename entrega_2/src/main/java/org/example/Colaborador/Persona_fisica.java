package org.example.Colaborador;


import java.util.Objects;

public class Persona_fisica extends Persona{
    String nombre;
    String apellido;
    String fecha_nacimiento;
    Documento_identidad documento_identidad;

    public Persona_fisica(String nombre, String apellido, String fecha_nacimiento,Documento_identidad documento, Medio_contacto[] mediosContacto,Domicilio domicilio){
        super(domicilio,mediosContacto);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public void setFechaNacimiento(String fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public Documento_identidad getDocumentoIdentidad(){
        return documento_identidad;
    }

    public Persona_fisica(String nombre, String apellido, Documento_identidad documento_identidad) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento_identidad = documento_identidad;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona_fisica personaFisica = (Persona_fisica) o;
        return Objects.equals(documento_identidad.getNumeroDocumento(), personaFisica.documento_identidad.getNumeroDocumento());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getDocumento_identidad().getNumeroDocumento());
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Documento_identidad getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(Documento_identidad documento_identidad) {
        this.documento_identidad = documento_identidad;
    }
}
