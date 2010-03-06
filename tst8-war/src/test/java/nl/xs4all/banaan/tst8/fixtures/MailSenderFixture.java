package nl.xs4all.banaan.tst8.fixtures;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.util.LinkedList;
import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Mock E-mail sender, does not send, but provides assertions over outbox.
 * and provide
 * @author konijn
 *
 */
public class MailSenderFixture implements MailSender {
    private List<SimpleMailMessage> outbox;
 
    public MailSenderFixture() {
        outbox = new LinkedList<SimpleMailMessage>();
    }
    
    public void send(SimpleMailMessage message) throws MailException {
        outbox.add(message);
    }

    public void send(SimpleMailMessage[] messages) throws MailException {
        for (SimpleMailMessage message : messages) {
            outbox.add(message);
        }
    }

    /*
     * verification routines. 
     */
    
    public void checkMessageCount (int expected) {
        assertThat("Message count", 
                outbox.size(), is(expected));
    }

    public void checkMessageTo(int i, String expected) {
        SimpleMailMessage message = getMessage(i);
        String[] toList = message.getTo();
        assertTrue("Messages to",
                toList.length == 1 && toList[0].equals(expected));
    }

    public void checkMessageSubject(int i, String expected) {
        SimpleMailMessage message = getMessage(i);
        String subject = message.getSubject();
        assertTrue("Message subject", subject.equals(expected));
    }

    public void checkMessageBodyContains(int i, String... patterns) {
        SimpleMailMessage message = getMessage(i);    
        String body = message.getText();
        for (String pattern : patterns) {
            assertThat("Message body", body, containsString(pattern));
        }
    }

    private SimpleMailMessage getMessage(int i) {
        assertTrue("Message exist", i >= 0 && i < outbox.size());
        return outbox.get(i);
    }
}
