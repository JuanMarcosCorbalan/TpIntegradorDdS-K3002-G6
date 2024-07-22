package org.example.Sistema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestsRegistroUsuarios {

    @Test
    void usuario_registrado() throws IOException {
        RegistrarUsuario.comprobar_registrar_usuario("rood92","Hola3456.");
        String contrasenia = RegistrarUsuario.usuarios_contrasenias.get("rood92");
        assertEquals("Hola3456.",contrasenia,"Se registra un usuario con exito!");
    }

    @Test
    void usuario_no_registrado() throws IOException {
        RegistrarUsuario.validar_usuario("arbolito23","Hola3456");
        boolean usuario_en_hash_map = RegistrarUsuario.usuarios_contrasenias.containsKey("arbolito23");
        assertFalse(usuario_en_hash_map,"Se rechaza el registro del usuario por contrasenia invalida");
    }
    //PODRIA IR UN CASO EN DONDE NO DEJE REGISTRARSE A UN USUARIO SI YA HAY UNO CON ESE NOMBRE DE USUARIO

}
