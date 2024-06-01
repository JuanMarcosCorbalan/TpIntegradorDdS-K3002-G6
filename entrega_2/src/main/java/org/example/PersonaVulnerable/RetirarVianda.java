package org.example.PersonaVulnerable;

import org.example.Heladeras.Heladera;

public class RetirarVianda {
    Heladera heladeraElegida;
    RetiroVianda retiro;
    TarjetaDuenio tarjetaUtilizada;

    public void retirarVianda(){
        tarjetaUtilizada.utilizar(retiro.getPersonaSV());
    }


    // CONSTRUCTOR
    public RetirarVianda(Heladera heladeraElegida, RetiroVianda retiro, TarjetaDuenio tarjetaUtilizada) {
        this.heladeraElegida = heladeraElegida;
        this.retiro = retiro;
        this.tarjetaUtilizada = tarjetaUtilizada;
    }
}
