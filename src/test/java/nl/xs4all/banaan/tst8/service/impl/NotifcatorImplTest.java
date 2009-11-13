package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import nl.xs4all.banaan.tst8.fixtures.InjectedTest;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.service.Notificator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test E-mail notification by scanning the outbox of a mock mailer. 
 * @author konijn
 *
 */
// TODO: the differenced between MailSender and MailSenderFixture
// means  the mailsender is not treated as singleton.
// this should be fixed by not doing inheritance in modules,
// just provide a list.
@Ignore
public class NotifcatorImplTest extends InjectedTest {
    private Notificator notificator;
    private MailSenderFixture mailSenderFixture;
    
    @Before
    public void setUp() {
        notificator = get(Notificator.class);
        mailSenderFixture = get(MailSenderFixture.class);
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
