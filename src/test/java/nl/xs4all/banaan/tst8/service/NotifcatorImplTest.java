package nl.xs4all.banaan.tst8.service;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;

import org.junit.After;
import org.junit.Before;
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

    private Notification notification1;
    private Notification notification2;

    @Before
    public void setUp() throws Exception {
        notification1 = new Notification ("test1@example.org",
                "this is subject1", "this is body1");
        notification2 = new Notification ("test2@example.org",
                "this is subject2", "this is body2");
    }

    @After
    public void tearDown() throws Exception {
    }

    @DirtiesContext
    @Test
    public void testSender() {
        // the sender starts of empty.
        mailSenderFixture.checkMessageCount(0);
    }
    
    @DirtiesContext
    @Test
    public void testSendOne() {
        notificator.send(notification1);
        mailSenderFixture.checkMessageCount(1);
    }
    
    @DirtiesContext
    @Test
    public void testSendTwo() {
        notificator.send(notification1);
        notificator.send(notification2);
        
        mailSenderFixture.checkMessageCount(2);
        mailSenderFixture.checkMessageTo(0, "test1@example.org");
        mailSenderFixture.checkMessageSubject(1, "this is subject2");
        mailSenderFixture.checkMessageBodyContains(1, "this", "body2");
    }

}
