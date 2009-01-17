package nl.xs4all.banaan.tst8.service;

/**
 * Send notifications via E-mail.
 * @author konijn
 *
 */
public interface Notificator {
    public void send (String to, String subject, String body);
}
