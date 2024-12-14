package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Donacion_viandas;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class DonacionViandaDAO {

    private EntityManager entityManager;

    public DonacionViandaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Donacion_viandas> findAllByColaborador(Colaborador colaborador) {
        List<Donacion_viandas> donacionViandas;
        try {
            donacionViandas = entityManager
                    .createQuery("select d from Donacion_viandas d where d.colaborador = :colaborador", Donacion_viandas.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            donacionViandas = null;
        }
        return donacionViandas;
    }
}
