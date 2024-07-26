package org.example.Validadores_Sensores;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Personal.BuscarTecnico;
import org.example.Personal.Tecnico;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FallaTecnica extends Incidente{
    Colaborador colaborador;
    String descripcion;
    File foto;
    Tecnico tecnicoAsignado;

    public FallaTecnica(Colaborador colaborador, String descripcion, File foto, Heladera heladera){
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
    }
}
