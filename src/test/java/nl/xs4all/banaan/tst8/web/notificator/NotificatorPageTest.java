package nl.xs4all.banaan.tst8.web.notificator;


import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.util.tester.FormTester;
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

    @DirtiesContext
    @Test
    public void testRenderNotificatorPage() {
        tester.startPage(NotificatorPage.class);
        tester.checkBasePage(NotificatorPage.class, "Send notifications");
        tester.assertComponent("notification", NotificationPanel.class);
    }

    /**
     * Test that submitting the form results in invoking notificator
     * with expected msg.
     */
    @DirtiesContext
    @Test
    public void testRenderNotificatorPage2() {
        Notification notification = new Notification(
                "test1@example.org",
                "this is subject1",
                "this is body1");
        Notificator notificator = createMock(Notificator.class);
        Services services = createMock(Services.class);
        expect(services.getNotificator()).andReturn(notificator);
        notificator.send(notification);
        replay(services, notificator);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", "test1@example.org");
        formTester.setValue("subject", "this is subject1");
        formTester.setValue("body", "this is body1");
        formTester.submit();
        
        verify(services, notificator);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertNoErrorMessage();
    }
}
