package org.example.Personal;

import org.example.Colaborador.Documento_identidad;
import org.example.Colaborador.Medio_contacto;
import org.example.Colaborador.Persona_fisica;

public class Tecnico extends Persona_fisica {
    AreaCobertura areaCobertura;


    public Tecnico(String nombre, String apellido, String fecha_nacimiento, Documento_identidad documento_identidad, String direccion, Medio_contacto[] mediosContacto, AreaCobertura areaCobertura ){
        super(nombre, apellido, fecha_nacimiento, documento_identidad,direccion,mediosContacto);

        this.areaCobertura = areaCobertura;
    }
    public void setMedioContacto(){}
    public void notificarPorMedio(){}
}
