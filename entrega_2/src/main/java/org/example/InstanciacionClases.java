package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Persona.Documento_identidad;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Tipo_documento;
import org.example.Sistema.MigracionColaboradores;

import java.util.List;

public class InstanciacionClases {
    // cree esta clase porque tenia muchos problemas con hacerlo en el main directamente poniendo los metodos
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
