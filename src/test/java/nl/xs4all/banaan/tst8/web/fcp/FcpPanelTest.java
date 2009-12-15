package nl.xs4all.banaan.tst8.web.fcp;


import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Experiment with FormComponentPanel.
 * @author konijn
 *
 */
public class FcpPanelTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() throws Exception {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
    @Test
    public void testFormPanelClickSubmitButton() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("name", "required");
        formTester.submit();
        assertEquals("required", FcpPanel.theObject.getName());

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
