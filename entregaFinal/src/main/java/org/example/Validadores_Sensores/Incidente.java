package org.example.Validadores_Sensores;

import jdk.jfr.Enabled;
import org.example.Heladeras.EstadoHeladera;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Personal.BuscarTecnico;
import org.example.Personal.Tecnico;
import org.example.Personal.Visita;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    Heladera heladera;

    LocalDate fecha;
    LocalTime hora;

    @Enumerated(EnumType.STRING)
    TipoIncidente tipoIncidente;

    @ManyToOne
    Tecnico tecnicoAsignado;

    @Enumerated(EnumType.STRING)
    EstadoIncidente estadoIncidente;

    @OneToMany(mappedBy = "incidente",cascade = CascadeType.ALL)
    List<Visita> visitas = new ArrayList<>();

    public Incidente(Heladera heladera, TipoIncidente tipoIncidente) {
        this.heladera = heladera;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = tipoIncidente;
        this.estadoIncidente = EstadoIncidente.NO_REPARADO;
        heladera.solicitarMantenimiento();
    }

    public Incidente() {
    }

    public Heladera getHeladera() {
        return heladera;
    }

    public LocalDate getFecha() { return fecha;
    }

    public EstadoIncidente getEstadoIncidente(){
        return estadoIncidente;
    }

    public LocalTime getHora() {return hora;
    }

    public void asignarTecnico(PuntoUbicacion ubicacion, List<Tecnico> tecnicos)
    {
        BuscarTecnico buscarTecnico = new BuscarTecnico();
        this.tecnicoAsignado = buscarTecnico.buscarTecnico(tecnicos,ubicacion);
        tecnicoAsignado.asignarIncidente(this);
    }
    public void reparar(){
        heladera.activar();
        this.estadoIncidente = EstadoIncidente.REPARADO;}
    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }
    public TipoIncidente getTipoIncidente(){return tipoIncidente;}

    public Long getId(){return id;}

    public void setEstadoIncidente(EstadoIncidente estadoIncidente) {
        this.estadoIncidente = estadoIncidente;
    }



}
