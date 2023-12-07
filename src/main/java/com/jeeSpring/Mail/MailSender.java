package com.jeeSpring.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void sendEmail(String to, String subject, String body) {
        // Configuration des propriétés du serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Remplacez par le serveur SMTP de votre fournisseur de messagerie
        properties.put("mail.smtp.port", "587"); // Port SMTP
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Création de la session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("javaee20112023@gmail.com", "eryo diho sryt smtz");
            }
        });

        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress("javaee20112023@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);

            System.out.println("Email envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
