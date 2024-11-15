package org.example.Persona;


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

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "persona_domicilio")
    public Domicilio domicilio;


    //@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    public List<Medio_contacto> mediosContacto;


    public Persona(Domicilio domicilio , List<Medio_contacto> mediosDeContacto)
    {
            this.domicilio = domicilio;
            this.mediosContacto = mediosDeContacto;
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

    @Override
    public boolean equals(Object o){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}

