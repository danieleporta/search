package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.wiring.TestModule;

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
        injector = new MockInjectorBuilder(new TestModule()).build();
        notificator = injector.get(Notificator.class);
        mailSenderFixture = injector.get(MailSenderFixture.class);
    }

    @Test
    public void testSenderStartsEmpty() {
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
