package org.example.Funcionalidades;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EnvioMail {
    public static void main(String[] args) throws IOException {


        Email from = new Email("grupo.hobbits@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("jbergara@frba.utn.edu.ar");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        String apiKey = "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";
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

}
