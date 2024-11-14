package org.example.Personal;

import org.example.Heladeras.Heladera;
import org.example.Persona.*;
import org.example.Validadores_Sensores.FallaTecnica;

import java.io.File;
import java.util.List;

public class Tecnico extends Rol {
    AreaCobertura areaCobertura;
    List <FallaTecnica> fallasTecnicasAsignadas;
    List <Visita> visitasRealizadas;

    public Tecnico(String nombre, String apellido, String fecha_nacimiento,Documento_identidad documento, List<Medio_contacto> mediosContacto, Domicilio domicilio, AreaCobertura areaCobertura ){
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
    
    public void asignarFalla(FallaTecnica fallaTecnica){
        fallasTecnicasAsignadas.add(fallaTecnica);
    }

    public void realizarVisitas(FallaTecnica fallaARevisar, Heladera heladera, String descripcion, Boolean incidenteSolucionado, File imagen)
    {
        Visita visitaRealizada = new Visita(fallaARevisar,heladera,descripcion,incidenteSolucionado,imagen);
        visitasRealizadas.add(visitaRealizada);
    }
}
