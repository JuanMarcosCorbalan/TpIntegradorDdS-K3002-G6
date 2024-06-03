package org.example.Modificacion;

import org.example.Colaborador.*;
import org.example.Personal.Tecnico;

public class Modificacion_colaborador_fisico {

    public void modificar_lat_dom(Colaborador colaborador, String lat_dom){
        colaborador.getPersona().getDomicilio().setLatitud(lat_dom);
    }
    public void modificar_long_dom(Colaborador colaborador, String long_dom){
        colaborador.getPersona().getDomicilio().setLongitud(long_dom);
    }
    public void modificar_pais(Colaborador colaborador, Pais pais){
        colaborador.getPersona().getDomicilio().setPais(pais);
    }
    public void modificar_ciudad(Colaborador colaborador, Ciudad ciudad){
        colaborador.getPersona().getDomicilio().setCiudad(ciudad);
    }
    public void modificar_direccion(Colaborador colaborador,String direccion) {
        colaborador.getPersona().getDomicilio().setDireccion(direccion);
    }
    public void modificar_medios(Colaborador colaborador, Medio_contacto[] mediosContacto) {
        colaborador.getPersona().setMediosDeContacto(mediosContacto);
    }
    public void modificar_nombre(Colaborador colaborador,String nombre){
        ((Persona_fisica) colaborador.getPersona()).setNombre(nombre);
    }
    public void modificar_apellido(Colaborador colaborador,String apellido){
        ((Persona_fisica) colaborador.getPersona()).setApellido(apellido);
    }
    public void modificar_fecha_nacimiento(Colaborador colaborador,String fechaNacimiento){
        ((Persona_fisica)colaborador.getPersona()).setFechaNacimiento(fechaNacimiento);
    }
    public void modificar_num_documento(Colaborador colaborador,String numDocumento){
        ((Persona_fisica)colaborador.getPersona()).getDocumentoIdentidad().setNumeroDocumento(numDocumento);
    }
    public void modificar_tipo_documento(Colaborador colaborador, Tipo_documento tipoDocumento){
        ((Persona_fisica)colaborador.getPersona()).getDocumentoIdentidad().setTipo(tipoDocumento);
    }

}
