package org.example.Personal;

import org.example.Heladeras.Heladera;
import org.example.Persona.*;
import org.example.Validadores_Sensores.FallaTecnica;
import org.example.Validadores_Sensores.Incidente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tecnico extends Rol {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerado e incremental
    public Long id;*/

    @ManyToOne (cascade = CascadeType.ALL)
    AreaCobertura areaCobertura;

    @OneToMany(mappedBy = "tecnicoAsignado", cascade = CascadeType.ALL)
    List <Incidente> incidentesAsignados = new ArrayList<Incidente>();

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
    
    public void asignarIncidente(Incidente incidente){
        incidentesAsignados.add(incidente);
    }

    public void realizarVisitas(FallaTecnica fallaARevisar, Heladera heladera, String descripcion, Boolean incidenteSolucionado, String imagen, Tecnico tecnico, LocalDate fecha)
    {
        Visita visitaRealizada = new Visita(fallaARevisar,heladera,descripcion,incidenteSolucionado,imagen,tecnico,fecha);
        visitasRealizadas.add(visitaRealizada);
    }

    public Long getId(){return id;}

    public List<Incidente> getIncidentesAsignados(){return incidentesAsignados;}
}
