package org.example.Colaborador;

import java.util.List;

public class RepositorioColaboradores {
    List<Colaborador> Colaboradores;

    public void agregarColaborador(Colaborador nuevoColaborador) {
        Colaboradores.add(nuevoColaborador);
    }

    public void eliminarColaborador(Colaborador colaboradorAEliminar) {
        Colaboradores.remove(colaboradorAEliminar);
    }

    public List<Colaborador> getColaboradores() {
        return Colaboradores;
    }

    public RepositorioColaboradores(List<Colaborador> colaboradores) {
        Colaboradores = colaboradores;
    }
}
