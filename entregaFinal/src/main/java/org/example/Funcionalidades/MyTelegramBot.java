package org.example.Funcionalidades;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "HobbitsHC_bot"; // Reemplaza con el nombre de tu bot
    }

    @Override
    public String getBotToken() {
        return "8074559067:AAG9MtiawvbtbwoIwUTV7hQan6R7HrGl0Yk"; // Reemplaza con el token de tu bot
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Aquí puedes manejar las respuestas de los usuarios
    }

    public void sendMessageToUser(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message); // Enviar el mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

/*
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(new MyTelegramBot());

            // Reemplaza con el ID del chat del usuario al que quieres enviar el mensaje
            String chatId = "123456789";  // Obtén el chat ID del usuario (puedes hacerlo interactuando con el bot primero)
            String message = "¡Hola desde tu bot!";
            new MyTelegramBot().sendMessageToUser(chatId, message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/
}

