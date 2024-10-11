package org.example.Heladeras;

import java.util.List;

public class RepositorioHeladeras {

    List<Heladera> heladeras;

    public List<Heladera> getHeladeras() {
        return heladeras;
    }

    public void aniadirHeladera(Heladera heladeraAAniadir){
        heladeras.add(heladeraAAniadir);
    }

    public void removerHeladera(Heladera heladeraARemover){
        heladeras.remove(heladeraARemover);
    }

}
