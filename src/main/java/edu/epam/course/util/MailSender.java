package edu.epam.course.util;

import edu.epam.course.exception.EmailException;
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
    private static final String MESSAGE_TEXT_FORGOT_PASSWORD = "Your password - ";
    private static final String MESSAGE_SUBJECT_FORGOT_PASSWORD = "Forgot password ";
    private static String username;
    private static String password;
    private static Properties properties;

    private MailSender() {
    }

    static  {
        properties = new Properties();
        try {
            ClassLoader classLoader = MailSender.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(MAIL_PROPERTIES);
            properties.load(resourceAsStream);
            username = properties.getProperty(USER_NAME_PROPERTIES);
            password = properties.getProperty(USER_PASSWORD_PROPERTIES);
        } catch (IOException e) {
            logger.error("Error uploading a file", e);
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String email, String name, String surname) throws EmailException {
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
            logger.error("Error generating or sending message: " + e);
            throw new EmailException(e);
        }
    }

    public static void sendPassword(String email, String userPassword) throws EmailException {
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
            message.setSubject(MESSAGE_SUBJECT_FORGOT_PASSWORD);
            message.setText(MESSAGE_TEXT_FORGOT_PASSWORD + userPassword);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: " + e);
            throw new EmailException(e);
        }
    }
}
