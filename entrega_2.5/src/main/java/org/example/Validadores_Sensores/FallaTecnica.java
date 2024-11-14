package org.example.Validadores_Sensores;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Personal.BuscarTecnico;
import org.example.Personal.Tecnico;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class FallaTecnica extends Incidente{

    @ManyToOne
    Colaborador colaborador;

    String descripcion;
    File foto;

    @ManyToOne
    Tecnico tecnicoAsignado;

    public FallaTecnica(Colaborador colaborador, String descripcion, File foto, Heladera heladera){
        super();
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.tipoIncidente = TipoIncidente.FALLA_TECNICA;
        this.heladera = heladera;
    }

    public void asignarTecnico(PuntoUbicacion ubicacion, List<Tecnico> tecnicos)
    {
        BuscarTecnico buscarTecnico = new BuscarTecnico();
        this.tecnicoAsignado = buscarTecnico.buscarTecnico(tecnicos,ubicacion);
        tecnicoAsignado.asignarFalla(this);
    }
}
