package org.example.Sistema;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.io.IOException;

public class UsuarioService {

    private EntityManager entityManager;
    private RegistrarUsuario servicioValidacion = new RegistrarUsuario(entityManager);

    public UsuarioService() {}

    public Usuario registrarUsuario(String nombreUsuario, String contrasenia) throws IOException {
        // Verificar si el nombre de usuario ya existe
        Usuario usuario = servicioValidacion.validar_nombre_usuario(nombreUsuario);
        if (usuario != null) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }
        if(!servicioValidacion.validar_contrasenia(contrasenia)){
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
    public boolean verificarContrasenia(String nombreUsuario, String contrasenia) {
        Usuario usuario = servicioValidacion.validar_nombre_usuario(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // Comparar la contraseña proporcionada con la almacenada
        return BCrypt.checkpw(contrasenia,usuario.getContraseniaHash());
    }

}
