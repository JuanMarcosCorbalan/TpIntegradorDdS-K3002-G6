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

    public void dar_alta_colaborador_fisico(String nombre, String apellido, String fechaNacimiento, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais , Forma_colaborar[] formas)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Persona_fisica nueva_persona = new Persona_fisica(nombre,apellido,fechaNacimiento,nuevo_documento,medios,nuevo_domicilio);
        Colaborador colaborador = new Colaborador(nueva_persona,formas);
        colaboradores.add(colaborador);
    }
    public void dar_alta_colaborador_juridico(String razonSocial, Tipo_juridico tipo, String rubro, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais , Forma_colaborar[] formas)
    {
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Persona_juridica nueva_persona = new Persona_juridica(nuevo_domicilio,medios,razonSocial,tipo,rubro);
        Colaborador colaborador = new Colaborador(nueva_persona,formas);
        colaboradores.add(colaborador);
    }
    void darBajaColaborador(Colaborador colaborador){
        colaboradores.remove(colaborador);
    }


    public void dar_alta_tecnico(String nombre, String apellido, String fechaNacimiento, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais ,String latitud, String longitud , String radio)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        AreaCobertura nueva_area = new AreaCobertura(latitud,longitud,radio);
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Tecnico nueva_tecnico = new Tecnico(nombre,apellido,fechaNacimiento,nuevo_documento,medios,nuevo_domicilio,nueva_area);
        tecnicos.add(nueva_tecnico);
    }
    void dar_baja_tecnico(Tecnico tecnico)
    {
        tecnicos.remove(tecnico);
    }



}