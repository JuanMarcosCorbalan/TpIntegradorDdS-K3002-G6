package org.example.Personal;

import org.example.Heladeras.Heladera;
import org.example.Persona.*;
import org.example.Validadores_Sensores.FallaTecnica;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tecnico extends Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerado e incremental
    public Long id;

    @ManyToOne (cascade = CascadeType.ALL)
    AreaCobertura areaCobertura;

    @OneToMany(mappedBy = "tecnicoAsignado", cascade = CascadeType.ALL)
    List <FallaTecnica> fallasTecnicasAsignadas = new ArrayList<FallaTecnica>();

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL)
    List <Visita> visitasRealizadas = new ArrayList<Visita>();

    public Tecnico(String nombre, String apellido, String fecha_nacimiento,Documento_identidad documento, List<Medio_contacto> mediosContacto, Domicilio domicilio, AreaCobertura areaCobertura ){
        this.persona = new Persona_fisica(nombre,apellido,fecha_nacimiento,documento,mediosContacto,domicilio);
        this.areaCobertura = areaCobertura;
    }

    public Tecnico() {

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

    public void realizarVisitas(FallaTecnica fallaARevisar, Heladera heladera, String descripcion, Boolean incidenteSolucionado, String imagen, Tecnico tecnico)
    {
        Visita visitaRealizada = new Visita(fallaARevisar,heladera,descripcion,incidenteSolucionado,imagen,tecnico);
        visitasRealizadas.add(visitaRealizada);
    }

    public Long getId(){return id;}

    public List<FallaTecnica> getFallasTecnicasAsignadas(){return fallasTecnicasAsignadas;}
}
