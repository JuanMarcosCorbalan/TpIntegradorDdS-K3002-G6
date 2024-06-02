package org.example.Colaborador;

public class Persona_fisica extends Persona{
    String nombre;
    String apellido;
    String fecha_nacimiento;
    Documento_identidad documento_identidad;

    public Persona_fisica(String nombre, String apellido, String fecha_nacimiento, Documento_identidad documento_identidad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento_identidad;
    }
}
