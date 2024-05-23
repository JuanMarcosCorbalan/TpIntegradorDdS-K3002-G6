package Formas_contribucion;

import colaborador.Colaborador;

abstract class Contribucion {
    Colaborador colaborador;
    String fecha_contribucion;
    Colaborador colaboradores_permitidos[];
    Colaborador colaboradores_dispuestos[];

    public void agregar_colaborador_permitido() {}
    public void agregar_colaborador_dispuesto() {}
    public void dispuesto_a_permitido() {}
    public void verificar_colaborador() {}
}
