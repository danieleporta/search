package nl.xs4all.banaan.tst8.web.notificator;


import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;

import org.apache.wicket.util.tester.FormTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotificatorPageTest {
    private Fixtures fixtures;
    private BasePageTester tester;
    private MailSenderFixture mailSenderFixture;
    
    @Before
    public void setUp() throws Exception {
        fixtures = Fixtures.get();
        tester = fixtures.getTester();
        mailSenderFixture = fixtures.getMailSenderFixture();
    }

    @After
    public void tearDown() throws Exception {
    }
    
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
