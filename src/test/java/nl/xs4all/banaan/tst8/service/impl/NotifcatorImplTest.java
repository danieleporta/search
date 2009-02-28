package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.*;
import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.service.Notificator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test E-mail notification by scanning the outbox of a mock mailer. 
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class NotifcatorImplTest {

    @Resource
    private Notificator notificator;
    
    @Resource
    private MailSenderFixture mailSenderFixture;

    @DirtiesContext
    @Test
    public void testSender() {
        // the sender starts of empty.
        mailSenderFixture.checkMessageCount(0);
    }
    
    @DirtiesContext
    @Test
    public void testSendOne() {
        notificator.send(NOTIFICATION1);
        mailSenderFixture.checkMessageCount(1);
    }
    
    @DirtiesContext
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
