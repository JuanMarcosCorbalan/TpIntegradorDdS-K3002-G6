package org.example.Personal;

import org.example.Persona.*;
import org.example.Validadores_Sensores.FallaTecnica;

public class Tecnico extends Rol {
    AreaCobertura areaCobertura;
    FallaTecnica[] fallasTecnicasAsignadas;
    Visita[] visitasRealizadas;

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
