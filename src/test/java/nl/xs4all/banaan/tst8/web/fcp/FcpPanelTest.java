package nl.xs4all.banaan.tst8.web.fcp;


import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Experiment with FormComponentPanel.
 * @author konijn
 *
 */
public class FcpPanelTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() throws Exception {
        injector = new MockInjector();
        tester = injector.get(WicketTester.class);
    }
    
    @Test
    public void testFcpPanelClickSubmitButton() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("name:input", "required");
        formTester.setValue("street:input", "also required");
        formTester.submit();
        assertEquals("required", FcpPanel.theObject.getName());
        assertEquals("also required", FcpPanel.theObject.getStreet());
    }
    
    /** create FcpPanel to be tested */
    private void makeTester() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new FcpPanel(panelId);
            }
        });
    }

}
