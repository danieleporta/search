package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.service.Notificator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test E-mail notification by scanning the outbox of a mock mailer. 
 * @author konijn
 *
 */
public class NotifcatorImplTest {
    private Notificator notificator;
    private MailSenderFixture mailSenderFixture;
    private MockInjector injector;
    
    @Before
    public void setUp() {
        injector = new MockInjector();
        notificator = injector.get(Notificator.class);
        mailSenderFixture = injector.get(MailSenderFixture.class);
    }

    @Test
    public void testSender() {
        // the sender starts of empty.
        mailSenderFixture.checkMessageCount(0);
    }

    @Test
    public void testSendOne() {
        notificator.send(NOTIFICATION1);
        mailSenderFixture.checkMessageCount(1);
    }
    
    @Test
    public void testSendTwo() {
        notificator.send(NOTIFICATION1);
        notificator.send(NOTIFICATION2);
        
        mailSenderFixture.checkMessageCount(2);
        mailSenderFixture.checkMessageTo(0, TO1);
        mailSenderFixture.checkMessageSubject(1, SUBJECT2);
        mailSenderFixture.checkMessageBodyContains(1, BODY2);
    }
}
