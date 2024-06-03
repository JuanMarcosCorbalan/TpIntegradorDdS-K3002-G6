package org.example.Modificacion;

import org.example.Colaborador.Medio_contacto;
import org.example.Colaborador.Tipo_documento;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;

import java.awt.geom.Area;

public class Modificacion_tecnico {


    public void modificar_direccion(Tecnico tecnico,String direccion) {
        tecnico.setDireccion(direccion);
    }
    public void modificar_medios(Tecnico tecnico, Medio_contacto[] mediosContacto) {
        tecnico.setMediosDeContacto(mediosContacto);
    }
    public void modificar_nombre(Tecnico tecnico,String nombre) {
        tecnico.setNombre(nombre);
    }
    public void modificar_apellidos(Tecnico tecnico,String apellido) {
        tecnico.setApellido(apellido);
    }
    public void modificar_fecha_nacimiento(Tecnico tecnico,String fechaNacimiento) {
        tecnico.setFechaNacimiento(fechaNacimiento);
    }
    public void modificar_num_documento(Tecnico tecnico,String numDocumento) {
        tecnico.getDocumentoIdentidad().setNumeroDocumento(numDocumento);
    }
    public void modificar_tipo_documento(Tecnico tecnico, Tipo_documento tipoDocumento) {
        tecnico.getDocumentoIdentidad().setTipo(tipoDocumento);
    }
    public void modificar_area_longitud(Tecnico tecnico,String longitud) {
        tecnico.getAreaCobertura().setLongitud(longitud);
    }
    public void modificar_area_latitud(Tecnico tecnico, String latitud){
        tecnico.getAreaCobertura().setLatitud(latitud);
    }
    public void modificar_area_radio(Tecnico tecnico,String radio){
        tecnico.getAreaCobertura().setRadio(radio);
    }
}
