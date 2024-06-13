package org.example.Modificacion;

import org.example.Colaborador.*;
import org.example.Personal.Tecnico;

public class Modificacion_colaborador_fisico extends Modificacion_persona{


    public void modificar_nombre(Colaborador colaborador,String nombre){
        ((Persona_fisica) colaborador.getPersona_colaboradora()).setNombre(nombre);
    }
    public void modificar_apellido(Colaborador colaborador,String apellido){
        ((Persona_fisica) colaborador.getPersona_colaboradora()).setApellido(apellido);
    }
    public void modificar_fecha_nacimiento(Colaborador colaborador,String fechaNacimiento){
        ((Persona_fisica)colaborador.getPersona_colaboradora()).setFechaNacimiento(fechaNacimiento);
    }
    public void modificar_num_documento(Colaborador colaborador,String numDocumento){
        ((Persona_fisica)colaborador.getPersona_colaboradora()).getDocumentoIdentidad().setNumeroDocumento(numDocumento);
    }
    public void modificar_tipo_documento(Colaborador colaborador, Tipo_documento tipoDocumento){
        ((Persona_fisica)colaborador.getPersona_colaboradora()).getDocumentoIdentidad().setTipo(tipoDocumento);
    }

}
