package org.example.Colaborador;

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

}
