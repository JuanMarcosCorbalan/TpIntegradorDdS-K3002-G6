package org.example.DAO;

import org.example.Tarjetas.Tarjeta;
import org.example.Sistema.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class UsuarioDAO {
    private EntityManager entityManager;

    public UsuarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Usuario findById(long id) {
        return entityManager.find(Usuario.class, id);
    }


    public Usuario find(String nombre_usuario, String contrasenia) {
        EntityTransaction transaction = entityManager.getTransaction();

        Usuario usuario = entityManager
                    .createQuery("select u from Usuario u " +
                            " where u.nombre = ?1 and u.contrasenia = ?2", Usuario.class)
                    .setParameter(1, nombre_usuario)
                    .setParameter(2, contrasenia)
                    .getSingleResult();
        return usuario;

    }

    public Usuario findByUsername(String nombre_usuario) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Inicia la transacción si es necesario
            if (!transaction.isActive()) {
                transaction.begin();
            }

            Usuario usuario = entityManager
                    .createQuery("select u from Usuario u where u.nombre = :nombre", Usuario.class)
                    .setParameter("nombre", nombre_usuario)
                    .getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra ningún usuario
            System.out.println("No se encontró un usuario con el nombre especificado.");
            return null;
        } catch (Exception e) {
            // Manejar excepciones generales
            e.printStackTrace();
            return null;
        } finally {
            if (transaction.isActive()) {
                transaction.commit();
            }
        }
    }



}
