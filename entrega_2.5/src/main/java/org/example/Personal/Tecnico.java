package org.example.Personal;

import org.example.Persona.*;

public class Tecnico extends Rol {
    AreaCobertura areaCobertura;


    public Tecnico(String nombre, String apellido, String fecha_nacimiento,Documento_identidad documento, Medio_contacto[] mediosContacto, Domicilio domicilio, AreaCobertura areaCobertura ){
        this.persona = new Persona_fisica(nombre,apellido,fecha_nacimiento,documento,mediosContacto,domicilio);
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
