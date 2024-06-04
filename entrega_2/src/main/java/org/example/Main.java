package org.example;

import org.example.Colaborador.*;
import org.example.Formas_contribucion.Contribucion;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Sistema.MigracionColaboradores;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();


    public static void main(String[] args) {
        List<Colaborador> colaboradoresNuevo = colaboradores;
        InstanciacionClases instanciacion = new InstanciacionClases();
        System.out.println("Hello world!");

        instanciacion.crearColaboradores(colaboradores);
        instanciacion.migrarColaboradores(colaboradores);


        for (Colaborador colaborador : colaboradores) {
            Persona persona = colaborador.getPersona_colaboradora();
            if(persona instanceof Persona_fisica personaFisicaExistente){
                System.out.println("DNI: " + personaFisicaExistente.getDocumento_identidad().getNumeroDocumento());
                String nombre = personaFisicaExistente.getNombre();
                List<Contribucion> contribuciones = colaborador.getContribuciones();
                if (!contribuciones.isEmpty()) System.out.println("Colaboraciones: ");
                else System.out.println("No tiene colaboraciones realizadas");
                for(Contribucion contribucion : contribuciones) {
                    System.out.println(contribucion.getFecha_contribucion());
                }
            }

        }

    }

    public void dar_alta_colaborador_fisico(String nombre, String apellido, String fechaNacimiento, String Direccion,
                                     Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto medios[], Forma_colaborar formas[]){
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        Persona_fisica nueva_persona = new Persona_fisica(nombre,apellido,fechaNacimiento,nuevo_documento);
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
    //falta cargarle los medios de contacto
    public void dar_alta_tecnico(String nombre, String apellido, String fechaNacimiento, String Direccion, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto medios[], String latitud,String longitud ,String radio)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        AreaCobertura nueva_area = new AreaCobertura(latitud,longitud,radio);
        Tecnico nueva_tecnico = new Tecnico(nombre,apellido,fechaNacimiento,nuevo_documento,nueva_area);

    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void migrarColaboradores(List<Colaborador> colaboradores){
        MigracionColaboradores migracionColaboradores = new MigracionColaboradores();
        migracionColaboradores.leerCsv(colaboradores);
    }
    public void crearColaboradores(List<Colaborador> colaboradores){
        Documento_identidad documentoJuan = new Documento_identidad("43871010", Tipo_documento.DNI);
        Persona_fisica persona1 = new Persona_fisica("Juan","Corbalan", documentoJuan);
        Documento_identidad documentoRaul = new Documento_identidad("45369874", Tipo_documento.DNI);
        Persona_fisica persona2 = new Persona_fisica("Raul","Perez", documentoRaul);
        Colaborador colaborador1 = new Colaborador(persona1);
        Colaborador colaborador2 = new Colaborador(persona2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
    }
}