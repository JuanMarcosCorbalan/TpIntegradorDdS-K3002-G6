package org.example.Persona;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;

@Entity
@Table (name = "Persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "persona_domicilio")
    public Domicilio domicilio;


    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Medio_contacto> mediosContacto;

    public Persona(Domicilio domicilio , List<Medio_contacto> mediosContacto)
    {
            this.domicilio = domicilio;
            this.mediosContacto = mediosContacto;
    }

    public Persona() {
    }

    public void setMediosDeContacto(List<Medio_contacto> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public List<Medio_contacto> getMediosContacto(){return mediosContacto;}

    //abstract void setNombre(String nombre);
    public void agregarMedioContacto(Medio_contacto medioContacto){
        mediosContacto.add(medioContacto);
    }

    @Override
    public boolean equals(Object o){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}

