package nl.xs4all.banaan.tst8.web.notificator;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.service.BuildInfo;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class NotificatorPageTest {
    @Resource
    private DemoApplication demoApplication;

    @Resource
    private BuildInfo buildInfo;
    
    private BasePageTester tester;

    @Before
    public void setUp() {
        tester = new BasePageTester(demoApplication);
    }

    @DirtiesContext
    @Test
    public void testRenderNotificatorPage() {
        tester.startPage(NotificatorPage.class);
        tester.checkBasePage(NotificatorPage.class, "Send notifications");
        tester.assertComponent("notification", NotificationPanel.class);
    }

    /**
     * Test that submitting the form results in invoking notificator
     * with expected message.
     */
    @DirtiesContext
    @Test
    public void testRenderNotificatorPage2() {
        Notificator notificator = createMock(Notificator.class);
        Services services = createMock(Services.class);
        expect(services.getNotificator()).andReturn(notificator);
        notificator.send(NOTIFICATION1);
        expect(services.getBuildInfo()).andReturn(buildInfo).times(2);
        replay(services, notificator);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services, notificator);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertNoErrorMessage();
    }
    
    @DirtiesContext
    @Test
    public void testEmptyToFieldIsDetected() {
        Services services = createMock(Services.class);
        expect(services.getBuildInfo()).andReturn(buildInfo).times(2);
        replay(services);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertErrorMessages(new String[]{"Field 'to' is required."});
    }
    
    @DirtiesContext
    @Test
    public void testEmptySubjectFieldIsDetected() {
        Services services = createMock(Services.class);
        expect(services.getBuildInfo()).andReturn(buildInfo).times(2);
        replay(services);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", TO1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertErrorMessages(new String[]{"Field 'subject' is required."});
    }
    
    @DirtiesContext
    @Test
    public void testEmptyBodyFieldIsDetected() {
        Services services = createMock(Services.class);
        expect(services.getBuildInfo()).andReturn(buildInfo).times(2);
        replay(services);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.submit();
        
        verify(services);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertErrorMessages(new String[]{"Field 'body' is required."});
    }

    @DirtiesContext
    @Test
    public void testBrokenToFieldIsDetected() {
        Services services = createMock(Services.class);
        expect(services.getBuildInfo()).andReturn(buildInfo).times(2);
        replay(services);
        
        demoApplication.setServices(services);
        tester.startPage(NotificatorPage.class);
        FormTester formTester = tester.newFormTester("notification:form");
        formTester.setValue("to", "This is not a love song");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertRenderedPage(NotificatorPage.class);
        tester.assertErrorMessages(new String[]{
                "'This is not a love song' is not a valid email address."});
    }
}
