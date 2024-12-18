package org.example.ValidarContrasenia;

public enum MensajeAviso {
    NO_DIGIT("La contrasenia debe contener al menos un numero"),
    NO_UPPERCASE("La contrasenia debe contener al menos una mayuscula"),
    NO_LOWERCASE("La contrasenia debe contener al menos una minuscula"),
    NO_SPECIAL_CHARACTER("La contrasenia debe contener al menos un caracter especial"),
    IN_TOP_TEN_THOUSAND("La contrasenia pertenece al top-10000 contrasenias"),
    LESS_THAN_EIGHT("La contrasenia debe contener al menos ocho caracteres");

    private final String message;

    MensajeAviso(String message) {
        this.message = message;
    }

    public String obtenerAdvertencia() {
        return message;
    }
}
