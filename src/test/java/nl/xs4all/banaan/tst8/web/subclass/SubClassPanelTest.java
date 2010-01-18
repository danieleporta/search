package nl.xs4all.banaan.tst8.web.subclass;


import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/** experiment with making subclass of a component with minimal HTML. */
public class SubClassPanelTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
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
