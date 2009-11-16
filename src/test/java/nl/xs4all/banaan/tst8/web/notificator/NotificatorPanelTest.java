package nl.xs4all.banaan.tst8.web.notificator;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BODY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NOTIFICATION1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.SUBJECT1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.TO1;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;
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
public class NotificatorPanelTest {
    private WicketMockInjector injector;
    private BasePageTester tester;
    private FormTester formTester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector(Services.class, Notificator.class);
        tester = injector.tester();
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
        final Notificator notificator = injector.get(Notificator.class);
        notificator.send(NOTIFICATION1);
        injector.replay();
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        injector.verify();
        tester.assertNoErrorMessage();
    }

    @Test
    public void testEmptyToFieldIsDetected() {
        injector.replay();
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        injector.verify();
        tester.assertErrorMessages(new String[]{"Field 'to' is required."});
    }
    
    @Test
    public void testEmptySubjectFieldIsDetected() {
        injector.replay();
        formTester.setValue("to", TO1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        injector.verify();
        tester.assertErrorMessages(new String[]{"Field 'subject' is required."});
    }
    
    @Test
    public void testEmptyBodyFieldIsDetected() {
        injector.replay();
        formTester.setValue("to", TO1);
        formTester.setValue("subject", SUBJECT1);
        formTester.submit();
        injector.verify();
        tester.assertErrorMessages(new String[]{"Field 'body' is required."});
    }

    @Test
    public void testBrokenToFieldIsDetected() {
        injector.replay();
        formTester.setValue("to", "This is not a love song");
        formTester.setValue("subject", SUBJECT1);
        formTester.setValue("body", BODY1);
        formTester.submit();
        injector.verify();
        tester.assertErrorMessages(new String[]{
                "'This is not a love song' is not a valid email address."});
    }
}
