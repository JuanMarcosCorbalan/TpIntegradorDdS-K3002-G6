package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;

import java.util.Date;

public class Contribucion {
    Colaborador colaborador;
    Date fecha_contribucion;
    Colaborador colaboradores_permitidos[];
    Colaborador colaboradores_dispuestos[];

    public void agregar_colaborador_permitido() {}
    public void agregar_colaborador_dispuesto() {}
    public void dispuesto_a_permitido() {}
    public void verificar_colaborador() {}
    public void realizar_contribucion(){};


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
