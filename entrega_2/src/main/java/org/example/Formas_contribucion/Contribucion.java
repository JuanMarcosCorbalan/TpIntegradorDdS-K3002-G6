package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Date;

public class Contribucion {
    //Colaborador colaborador; CAMBIO RESPECTO A ENTREGA 2
    Date fecha_contribucion;
    List<Colaborador> colaboradores_permitidos = new ArrayList<Colaborador>();
    List<Colaborador> colaboradores_dispuestos = new ArrayList<Colaborador>();

    public void agregar_colaborador_permitido() {}
    public void agregar_colaborador_dispuesto() {}
    public void dispuesto_a_permitido() {}
    public Boolean verificar_colaborador(Colaborador colaborador) {
        return colaboradores_permitidos.contains(colaborador);
    }
    public void realizar_contribucion (){};
    public void verificar_colaborador() {}


    public Contribucion(Date fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }

    public Contribucion() {
    }

    public Date getFecha_contribucion() {
        return fecha_contribucion;
    }

    public void setFecha_contribucion(Date fecha_contribucion) {
        this.fecha_contribucion = fecha_contribucion;
    }
}
