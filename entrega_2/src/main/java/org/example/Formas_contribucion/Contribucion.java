package org.example.Formas_contribucion;

import org.example.Colaborador.Colaborador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contribucion {
    //Colaborador colaborador; CAMBIO RESPECTO A ENTREGA 2
    String fecha_contribucion;
    List<Colaborador> colaboradores_permitidos = new ArrayList<Colaborador>();
    List<Colaborador> colaboradores_dispuestos = new ArrayList<Colaborador>();

    public void agregar_colaborador_permitido() {}
    public void agregar_colaborador_dispuesto() {}
    public void dispuesto_a_permitido() {}
    public Boolean verificar_colaborador(Colaborador colaborador) {
        return colaboradores_permitidos.contains(colaborador);
    }
    public void realizar_contribucion (){

    };
}
