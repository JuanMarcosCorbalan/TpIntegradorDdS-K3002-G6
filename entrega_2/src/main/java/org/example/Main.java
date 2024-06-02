package org.example;

import org.example.Colaborador.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();


    public void dar_alta_colaborador_fisico(String nombre, String apellido, String fechaNacimiento, String Direccion,
                                     Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto medios[], Forma_colaborar formas[]){
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        Persona_fisica nueva_persona = new Persona_fisica(nombre,apellido,fechaNacimiento,nuevo_documento);
        //FALTARIA EL CONSTRUCTOR DE COLABORADOR
        Colaborador colaborador = new Colaborador(nueva_persona,medios,formas);
        colaboradores.add(colaborador);
    }
/*
    void darBajaColaborador(Colaborador colaborador){
        colaboradores.remove(colaborador);
    }

    void modificarColaborador(Colaborador colaborador){
        colaboradores.
    }*/

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}