package nl.xs4all.banaan.tst8.web.notificator;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

public class NotificatorPageTest extends SpringJUnitWicketTest {
    
    @Before @Override
    public void setUp() {
        super.setUp();
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new NotificationPanel(panelId);
            }
        });
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
        replay(services, notificator);
        
        demoApplication.setServices(services);
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services, notificator);
        tester.assertNoErrorMessage();
    }
    
    @DirtiesContext
    @Test
    public void testEmptyToFieldIsDetected() {
        Services services = createMock(Services.class);
        replay(services);
        
        demoApplication.setServices(services);
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'to' is required."});
    }
    
    @DirtiesContext
    @Test
    public void testEmptySubjectFieldIsDetected() {
        Services services = createMock(Services.class);
        replay(services);
        
        demoApplication.setServices(services);
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("to", TO1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'subject' is required."});
    }
    
    @DirtiesContext
    @Test
    public void testEmptyBodyFieldIsDetected() {
        Services services = createMock(Services.class);
        replay(services);
        
        demoApplication.setServices(services);
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'body' is required."});
    }

    @DirtiesContext
    @Test
    public void testBrokenToFieldIsDetected() {
        Services services = createMock(Services.class);
        replay(services);
        
        demoApplication.setServices(services);
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("to", "This is not a love song");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{
                "'This is not a love song' is not a valid email address."});
    }
}
