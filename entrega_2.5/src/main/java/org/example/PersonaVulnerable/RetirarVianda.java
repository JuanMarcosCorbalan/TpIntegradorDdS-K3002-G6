package org.example.PersonaVulnerable;

import org.example.Heladeras.Heladera;

public class RetirarVianda {
    Heladera heladeraElegida;
    RetiroVianda retiro;
    Tarjeta tarjetaUtilizada;

    public void retirarVianda(){
        tarjetaUtilizada.utilizar(heladeraElegida);
    }


    // CONSTRUCTOR
    public RetirarVianda(Heladera heladeraElegida, RetiroVianda retiro, Tarjeta tarjetaUtilizada) {
        this.heladeraElegida = heladeraElegida;
        this.retiro = retiro;
        this.tarjetaUtilizada = tarjetaUtilizada;

    }
}
