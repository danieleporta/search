package nl.xs4all.banaan.tst8.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class NotificatorImpl implements Notificator {

    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
