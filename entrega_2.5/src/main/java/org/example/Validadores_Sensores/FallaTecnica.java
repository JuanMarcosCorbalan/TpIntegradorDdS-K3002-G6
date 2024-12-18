package org.example.Validadores_Sensores;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Personal.BuscarTecnico;
import org.example.Personal.Tecnico;
import org.example.Personal.Visita;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FallaTecnica extends Incidente{

    @ManyToOne
    Colaborador colaborador;

    String descripcion;
    String foto;

    @ManyToOne
    Tecnico tecnicoAsignado;

    @OneToMany(mappedBy = "fallaRevisada",cascade = CascadeType.ALL)
    List<Visita> visitas = new ArrayList<>();


    public FallaTecnica(Colaborador colaborador, String descripcion, String foto, Heladera heladera){
        super();
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = TipoIncidente.FALLA_TECNICA;
        this.heladera = heladera;
    }

    public FallaTecnica(Colaborador colaborador, String descripcion, Heladera heladera){
        super();
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = TipoIncidente.FALLA_TECNICA;
        this.heladera = heladera;
    }

    public FallaTecnica() {

    }
    public void agregarVisita(Visita visita)
    {
        visitas.add(visita);
    }
    public void asignarTecnico(PuntoUbicacion ubicacion, List<Tecnico> tecnicos)
    {
        BuscarTecnico buscarTecnico = new BuscarTecnico();
        this.tecnicoAsignado = buscarTecnico.buscarTecnico(tecnicos,ubicacion);
        tecnicoAsignado.asignarFalla(this);
    }

    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public String getDescripcion() {return descripcion;
    }

    public String getFoto() {return foto;
    }
}
