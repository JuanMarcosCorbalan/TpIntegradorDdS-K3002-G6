package org.example.Persona;

import org.example.Funcionalidades.MyTelegramBot;
import org.example.Suscripcion.MensajeAviso;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.persistence.Entity;

@Entity
public class Telegram extends Medio_contacto{

    public String id_chat;
    public String id_user;

    public Telegram(Persona persona,String id_chat, String id_user) {
        super(persona);
        this.id_chat = id_chat;
        this.id_user = id_user;
    }

    public Telegram() {

    }

    public String getId_chat() {return id_chat;}
    public String getId_user() {return id_user;}

    public void setId_chat(String id_chat) {this.id_chat = id_chat;}
    public void setId_user(String id_user) {this.id_user = id_user;}

    @Override
    public void notificar (MensajeAviso mensaje) {
        System.out.println("Notificando Telegram");

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyTelegramBot());
            String message = "\uD83D\uDCE3 Hola "+id_user+"! \uD83D\uDC4B \n"+mensaje.getDescripcionMensaje();
            new MyTelegramBot().sendMessageToUser(id_chat, message);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
