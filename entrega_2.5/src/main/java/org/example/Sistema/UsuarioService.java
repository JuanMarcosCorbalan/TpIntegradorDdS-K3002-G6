package org.example.Sistema;

import org.example.Colaborador.Colaborador;
import org.example.DAO.CiudadDAO;
import org.example.Persona.Ciudad;
import org.example.Persona.Localidad;
import org.example.Persona.Persona;
import org.example.Persona.Rol;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Objects;

public class UsuarioService {

    private EntityManager entityManager;
    private RegistrarUsuario servicioValidacion;

    public UsuarioService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.servicioValidacion = new RegistrarUsuario(entityManager);
    }

    public Usuario registrarUsuario(String nombreUsuario, String contrasenia) throws IOException {
        // Verificar si el nombre de usuario ya existe
        Usuario usuario = servicioValidacion.validar_nombre_usuario(nombreUsuario);
        if (usuario != null) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }
        if (!servicioValidacion.validar_contrasenia(contrasenia)) {
            throw new RuntimeException("La contraseña no es valida");
        }

        // Hashear la contraseña
        String contraseniaHash = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

        // Crear nuevo usuario
        usuario = new Usuario(null, nombreUsuario, contraseniaHash);

        // Persistir el nuevo usuario
        entityManager.persist(usuario); // todavia falta asignarle la persona


        return usuario;
    }


    // Método para verificar la contraseña, para inicio de sesion
    public Usuario verificarInicioSesion(String nombreUsuario, String contrasenia) {
        Usuario usuario = servicioValidacion.validar_nombre_usuario(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // Comparar la contraseña proporcionada con la almacenada
        if (!servicioValidacion.validar_contrasenia_usuario(usuario, contrasenia)) {
            throw new RuntimeException("Contraseña Incorrecta");
        }
        return usuario;
    }


    public Colaborador obtenerColaborador(Usuario usuario) {

        Colaborador colaborador = null;
        try {
            colaborador = entityManager
                    .createQuery("select c from Usuario u join u.persona p " +
                            "join Colaborador c on " +
                            "  c.id = p.id where u.nombre = ?1", Colaborador.class)
                    .setParameter(1, usuario.getNombre())
                    .getSingleResult();
        } catch (NoResultException e) {
            // quizas habria que verificar que no exista el colaborador pero no tendria que pasar
        }

        return colaborador;


    }

    public Rol obtenerRolAsociado(Usuario usuario) {

        Rol rol = null;
        try {
            rol = entityManager
                    .createQuery("select r from Usuario u join u.persona p " +
                            "join Rol r on " +
                            "  r.persona.id = p.id where u.nombre = ?1", Rol.class)
                    .setParameter(1, usuario.getNombre())
                    .getSingleResult();
        } catch (NoResultException e) {
            // quizas habria que verificar que no exista el colaborador pero no tendria que pasar
        }

        return rol;


    }

    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Mail m join m.persona p join Usuario u on p.id = u.persona " +
                                    " where  m.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // El usuario no existe
        }
    }

    public void esFisico(Colaborador colaborador) {
        /*
        Bool fisico = null;
        fisico = entityManager
                .createQuery("select c from Colaborador c join c.persona p " +
                        "where c.id = ?1", Colaborador.class)
                .setParameter(1, colaborador.getId())
                .getSingleResult();
    */
    }

}
