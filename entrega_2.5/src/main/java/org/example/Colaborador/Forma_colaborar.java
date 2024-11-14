package org.example.Colaborador;

import javax.persistence.Entity;

@Entity
public enum Forma_colaborar {
    DONACION_DINERO,
    DONACION_VIANDAS,
    DISTRIBUCION_VIANDAS,
    HACERSE_CARGO_HELADERA;
}
