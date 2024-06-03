package org.example.Personal;

import org.example.Colaborador.*;

public class Tecnico extends Persona_fisica {
    AreaCobertura areaCobertura;


    public Tecnico(String nombre, String apellido, String fecha_nacimiento,Documento_identidad documento, Medio_contacto[] mediosContacto, Domicilio domicilio, AreaCobertura areaCobertura ){
        super(nombre,apellido,fecha_nacimiento,documento,mediosContacto,domicilio);
        this.areaCobertura = areaCobertura;
    }
    public void setAreaCobertura(AreaCobertura areaCobertura){
        this.areaCobertura = areaCobertura;
    }
    public AreaCobertura getAreaCobertura(){
        return areaCobertura;
    }
    public void notificarPorMedio(){}
}
