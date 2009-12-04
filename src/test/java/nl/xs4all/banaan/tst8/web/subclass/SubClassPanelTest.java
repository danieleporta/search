package nl.xs4all.banaan.tst8.web.subclass;


import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

/** experiment with making subclass of a component with minimal HTML. */
public class SubClassPanelTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
    @Test
    public void testShowPanel() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new SubClassPanel(panelId);
            }
        });
        tester.assertComponent("panel:inner", Component.class);
    }
}
