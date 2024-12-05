package org.example.Colaborador.ControladoresColaborador;

import org.example.Conversores.Convertidor;
import org.example.Persona.Domicilio;
import org.example.Persona.Persona_fisica;
import org.example.Sistema.RegistroDTO;
import org.example.Sistema.Usuario;
import org.example.Sistema.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class RegistroUsuariosController {

    private EntityManager entityManager;

    @PostMapping("/registro") // reivsar esto
    public ResponseEntity<String> procesarRegistro(@RequestBody RegistroDTO registroDTO, Model model) throws IOException {
        // Aquí procesas el registro (por ejemplo, guardar en base de datos)
        // creo el usuario
        UsuarioService us = new UsuarioService();
        Usuario user = null;
        try{
            user = us.registrarUsuario(registroDTO.getUsuario(), registroDTO.getContrasenia());
        } catch (RuntimeException e){
            return ResponseEntity.ok(e.getMessage());
        }
        // ahora deberia crear a la persona fisica
        // obtener medios

        // obtener documento
        // falta la cajita de doc


        // obtener domicilio
        Convertidor conv = new Convertidor(entityManager);
        Domicilio dom = conv.DTOtoDomicilio(registroDTO.getCalle(),registroDTO.getAltura(),registroDTO.getLocalidad(),registroDTO.getCiudad(),registroDTO.getPais());

        Persona_fisica pf = new Persona_fisica(registroDTO.getNombres(), registroDTO.getApellidos(),registroDTO.getFechaNacimiento(),null,null,dom);
        entityManager.persist(pf);
        user.setPersona(pf);
        entityManager.persist(user);
        // Luego puedes preparar los datos para la plantilla Mustache

        model.addAttribute("usuario", registroDTO.getUsuario());
        model.addAttribute("nombres", registroDTO.getNombres());
        model.addAttribute("apellidos", registroDTO.getApellidos());
        model.addAttribute("correo", registroDTO.getCorreo());

        // Retornamos una vista con Mustache (por ejemplo, una vista de confirmación)
        return ResponseEntity.ok("registroExitoso"); // Redirige a una página de éxito o muestra un mensaje.
    }
}

