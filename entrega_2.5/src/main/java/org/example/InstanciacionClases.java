package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.RepositorioColaboradores;
import org.example.Persona.Documento_identidad;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Tipo_documento;
import org.example.Sistema.MigracionColaboradores;

import java.util.List;

public class InstanciacionClases {
    RepositorioColaboradores colaboradores;

    // cree esta clase porque tenia muchos problemas con hacerlo en el main directamente poniendo los metodos
    public void migrarColaboradores(String pathCsv, RepositorioColaboradores repoCcolaboradores){
        MigracionColaboradores migracionColaboradores = new MigracionColaboradores(pathCsv, repoCcolaboradores);
        migracionColaboradores.migrarCsv();
    }
    public void crearColaboradores(){
        Documento_identidad documentoJuan = new Documento_identidad("43871010", Tipo_documento.DNI);
        Persona_fisica persona1 = new Persona_fisica("Juan","Corbalan", documentoJuan);
        Documento_identidad documentoRaul = new Documento_identidad("45369874", Tipo_documento.DNI);
        Persona_fisica persona2 = new Persona_fisica("Raul","Perez", documentoRaul);
        Colaborador colaborador1 = new Colaborador(persona1);
        Colaborador colaborador2 = new Colaborador(persona2);
        colaboradores.agregarColaborador(colaborador1);
        colaboradores.agregarColaborador(colaborador2);
    }
}
