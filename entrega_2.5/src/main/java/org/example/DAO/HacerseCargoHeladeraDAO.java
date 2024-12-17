package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.HacerseCargoHeladera;
import org.example.DAO.HacerseCargoHeladeraDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HacerseCargoHeladeraDAO {

    private EntityManager entityManager;

    public HacerseCargoHeladeraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<HacerseCargoHeladera> findAllByColaborador(Colaborador colaborador) {
        List<HacerseCargoHeladera> hacerseCargoHeladera;
        try {
            hacerseCargoHeladera = entityManager
                    .createQuery("select d from HacerseCargoHeladera d where d.colaborador = :colaborador", HacerseCargoHeladera.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            hacerseCargoHeladera = null;
        }
        return hacerseCargoHeladera;
    }

}
