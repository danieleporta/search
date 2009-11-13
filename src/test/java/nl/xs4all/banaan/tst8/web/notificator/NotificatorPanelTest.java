package nl.xs4all.banaan.tst8.web.notificator;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import nl.xs4all.banaan.tst8.fixtures.InjectedWicketTest;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

public class NotificatorPanelTest extends InjectedWicketTest {
   
    /** services will be filled with expectations for every test */
    private Services services;
    private FormTester formTester;

    @Before @Override
    public void setUp() {
        super.setUp();
        services = createMock(Services.class);
        demoApplication.setServices(services);
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new NotificationPanel(panelId);
            }
        });
        formTester = tester.newFormTester("panel:form");
    }
    
    /**
     * Test that submitting the form results in invoking notificator
     * with expected message.
     */
    @Test
    public void testRenderNotificatorPage2() {
        Notificator notificator = createMock(Notificator.class);
        expect(services.getNotificator()).andReturn(notificator);
        notificator.send(NOTIFICATION1);
        replay(services, notificator);
        
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services, notificator);
        tester.assertNoErrorMessage();
    }
    
    @Test
    public void testEmptyToFieldIsDetected() {
        replay(services);
        
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'to' is required."});
    }
    
    @Test
    public void testEmptySubjectFieldIsDetected() {
        replay(services);
        
        formTester.setValue("to", TO1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'subject' is required."});
    }
    
    @Test
    public void testEmptyBodyFieldIsDetected() {
        replay(services);
        
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{"Field 'body' is required."});
    }

    @Test
    public void testBrokenToFieldIsDetected() {
        replay(services);
        
        formTester.setValue("to", "This is not a love song");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        
        verify(services);
        tester.assertErrorMessages(new String[]{
                "'This is not a love song' is not a valid email address."});
    }
}
