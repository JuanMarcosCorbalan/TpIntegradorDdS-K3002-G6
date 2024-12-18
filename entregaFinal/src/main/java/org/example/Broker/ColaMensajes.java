package org.example.Broker;
import java.util.LinkedList;
import java.util.Queue;
public class ColaMensajes<T> {
    // Utilizamos una cola (Queue) genérica para almacenar los mensajes
    private Queue<T> cola;

    // Constructor
    public ColaMensajes() {
        this.cola = new LinkedList<>();
    }

    // Método para encolar un mensaje
    public void agregar_mensaje(T mensaje) {
        cola.add(mensaje);
    }

    // Método para desencolar un mensaje
    public T obtener_mensaje() {
        if (!cola.isEmpty()) {
            T mensaje = cola.poll();
            return mensaje;
        } else {
            System.out.println("La cola está vacía.");
            return null;
        }
    }

    // Método para revisar si la cola está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // Método para ver el próximo mensaje sin desencolarlo
    public T verPrimero() {
        return cola.peek();
    }
}
