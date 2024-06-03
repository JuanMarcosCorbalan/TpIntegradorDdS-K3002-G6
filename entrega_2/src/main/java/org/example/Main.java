package org.example;

import org.example.Colaborador.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class Main {

    List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    List<Tecnico> tecnicos = new ArrayList<Tecnico>();
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();



    public static void main(String[] args) {

        System.out.println("Hello world!");

    }

    public void dar_alta_colaborador_fisico(String nombre, String apellido, String fechaNacimiento, String Direccion,
                                            Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios, Forma_colaborar[] formas){
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        Persona_fisica nueva_persona = new Persona_fisica(nombre,apellido,fechaNacimiento,nuevo_documento,Direccion,medios);
        Colaborador colaborador = new Colaborador(nueva_persona,formas);
        colaboradores.add(colaborador);
    }
/*
    void darBajaColaborador(Colaborador colaborador){
        colaboradores.remove(colaborador);
    }

    void modificarColaborador(Colaborador colaborador){
        colaboradores.
    }*/

    public void dar_alta_tecnico(String nombre, String apellido, String fechaNacimiento, String direccion, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios, String latitud, String longitud , String radio)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        AreaCobertura nueva_area = new AreaCobertura(latitud,longitud,radio);
        Tecnico nueva_tecnico = new Tecnico(nombre,apellido,fechaNacimiento,nuevo_documento,direccion,medios,nueva_area);
        tecnicos.add(nueva_tecnico);
    }
    void dar_baja_tecnico(Tecnico tecnico)
    {
        tecnicos.remove(tecnico);
    }


}