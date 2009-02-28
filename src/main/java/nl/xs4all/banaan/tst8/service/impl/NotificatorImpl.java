package nl.xs4all.banaan.tst8.service.impl;

import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.service.Notificator;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class NotificatorImpl implements Notificator {

    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void send(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getTo());
        message.setSubject(notification.getSubject());
        message.setText(notification.getBody());
        mailSender.send(message);
    }
}
