package org.example.Funcionalidades;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EnvioMail {


    public void enviarEmail(Email email) throws IOException {
        Email from = new Email("grupo.hobbits@gmail.com");
        String subject = "Carga realizada correctamente";
        Content content = new Content("text/plain", "Tu usuario fue cargado correctamente.");
        Mail mail = new Mail(from, subject, email, content);

        String apiKey = "insertarApiKey";
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
    // CASO DE PRUEBA
    public static void main(String[] args) throws IOException {
        Email from = new Email("grupo.hobbits@gmail.com");
        String subject = "Carga realizada correctamente";
        Email to = new Email("rrodasgaleano@frba.utn.edu.ar");
        Content content = new Content("text/plain", "Tu usuario fue cargado correctamente.");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("INSERTARAPIKEY");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
