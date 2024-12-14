package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.Donacion_viandas;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class DistribucionViandasDAO {

    private EntityManager entityManager;

    public DistribucionViandasDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Distribucion_viandas> findAllByColaborador(Colaborador colaborador) {
        List<Distribucion_viandas> distribucionViandas;
        try {
            distribucionViandas = entityManager
                    .createQuery("select d from Distribucion_viandas d where d.colaborador = :colaborador", Distribucion_viandas.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            distribucionViandas = null;
        }
        return distribucionViandas;
    }
}
