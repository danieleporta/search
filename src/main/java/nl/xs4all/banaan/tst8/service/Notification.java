package nl.xs4all.banaan.tst8.service;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * A notification is a message sent by the application via E-mail.
 * @author konijn
 *
 */
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Notification.class);
    
    private String to;
    private String subject;
    private String body;
    
    public Notification () {
    }

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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        logger.debug ("Notification equals called");
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Notification other = (Notification) obj;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (to == null) {
            if (other.to != null)
                return false;
        } else if (!to.equals(other.to))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Notification[" 
            + "to=" + to + ","  
            + "subject=" + subject + ","
            + "body=" + body
            + "]";
    }

    
}
