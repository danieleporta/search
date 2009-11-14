package nl.xs4all.banaan.tst8.web.notificator;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import static org.easymock.EasyMock.expect;
import nl.xs4all.banaan.tst8.fixtures.MockInjectedWicketTest;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.Services;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that UI invokes proper methods from service layer.
 * @author konijn
 *
 */
public class NotificatorPanelTest extends MockInjectedWicketTest {
    private Services services;
    private Notificator notificator;
    private FormTester formTester;

    @Before
    public void setUp() {
        mock(Services.class, Notificator.class);
        services = get(Services.class);
        notificator = get(Notificator.class);
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new NotificationPanel(panelId);
            }
        });
        formTester = tester.newFormTester("panel:form");
    }

    @Test
    public void testSubmittingFormDoesNotificatorWithExpectedMessage() {
        expect(services.getNotificator()).andReturn(notificator);
        notificator.send(NOTIFICATION1);
        replay();
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        verify();
        tester.assertNoErrorMessage();
    }

    @Test
    public void testEmptyToFieldIsDetected() {
        replay();
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        verify();
        tester.assertErrorMessages(new String[]{"Field 'to' is required."});
    }
    
    @Test
    public void testEmptySubjectFieldIsDetected() {
        replay();
        formTester.setValue("to", TO1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        verify();
        tester.assertErrorMessages(new String[]{"Field 'subject' is required."});
    }
    
    @Test
    public void testEmptyBodyFieldIsDetected() {
        replay();
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.submit();
        verify();
        tester.assertErrorMessages(new String[]{"Field 'body' is required."});
    }

    @Test
    public void testBrokenToFieldIsDetected() {
        replay();
        formTester.setValue("to", "This is not a love song");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        verify();
        tester.assertErrorMessages(new String[]{
                "'This is not a love song' is not a valid email address."});
    }
}
