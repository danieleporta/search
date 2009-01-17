package nl.xs4all.banaan.tst8.service;

/**
 * A notification is a message sent by the application via E-mail.
 * @author konijn
 *
 */
public class Notification {
    private String to;
    private String subject;
    private String body;
    
    public Notification (String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
