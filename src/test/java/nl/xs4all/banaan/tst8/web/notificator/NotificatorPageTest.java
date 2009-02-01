package nl.xs4all.banaan.tst8.web.notificator;


import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.fixtures.FlightRecorder;
import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.util.tester.FormTester;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class NotificatorPageTest {
    @Resource
    private BasePageTester tester;
    
    @Resource
    private DemoApplication demoApplication;
    
    @Resource
    private Notificator notificator;
    
    @Resource
    private MailSenderFixture mailSenderFixture;

    @Test
    public void testRenderNotificatorPage() {
        tester.startPage(NotificatorPage.class);
        tester.checkBasePage(NotificatorPage.class, "Send notifications");
        tester.assertComponent("notification", NotificationPanel.class);
    }

    /**
     * Test that submitting the form results in mail in the outbox.
     * unsure about this one: perhaps it's cleaner for a unit test
     * to just verify the interaction with service layer.
     */
    @DirtiesContext
    @Test
    public void testRenderNotificatorPage2() {
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", "test1@example.org");
        formTester.setValue("subject", "this is subject1");
        formTester.setValue("body", "this is body1");
        formTester.submit();
        
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertNoErrorMessage();
        mailSenderFixture.checkMessageCount(1);
        mailSenderFixture.checkMessageTo(0, "test1@example.org");
        mailSenderFixture.checkMessageSubject(0, "this is subject1");
        mailSenderFixture.checkMessageBodyContains(0, "body1");
    }
}
