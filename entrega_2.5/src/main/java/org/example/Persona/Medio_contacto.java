package org.example.Persona;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;

//@Entity
public interface Medio_contacto {



    void notificar(Medio_contacto[] medios_contacto) throws IOException;
}

