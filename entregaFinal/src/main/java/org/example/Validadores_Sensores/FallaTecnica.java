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






    public FallaTecnica(Colaborador colaborador, String descripcion, String foto, Heladera heladera){
        super(heladera,TipoIncidente.FALLA_TECNICA);
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.foto = foto;
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


    public String getDescripcion() {return descripcion;
    }

    public String getFoto() {return foto;
    }

}
