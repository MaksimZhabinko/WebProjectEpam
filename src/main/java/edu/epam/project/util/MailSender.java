package edu.epam.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_PROPERTIES = "mail.properties";
    private static final String USER_NAME_PROPERTIES = "mail.user.name";
    private static final String USER_PASSWORD_PROPERTIES = "mail.user.password";
    private static final String MESSAGE_SUBJECT = "Thank you for registering";
    private static final String MESSAGE_TEXT = "Thank you for registering ";
    private String username;
    private String password;
    private Properties properties;

    public MailSender() {
        properties = new Properties();
        try {
            ClassLoader classLoader = MailSender.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(MAIL_PROPERTIES);
            properties.load(resourceAsStream);
        } catch (IOException e) {
            logger.error("Error uploading a file", e);
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String email, String name, String surname) {
        username = properties.getProperty(USER_NAME_PROPERTIES);
        password = properties.getProperty(USER_PASSWORD_PROPERTIES);
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(MESSAGE_SUBJECT);
            message.setText(MESSAGE_TEXT + name + " " + surname);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: " + e); // todo нужно ли тут кидать Runtime?
        }
    }
}
