package nl.xs4all.banaan.tst8.web.param;



import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.http.MockServletContext;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class ParamPanelTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
    }
    
    @Test
    public void testRenderingAnInitParameter() {
        addInitParameter(tester, "aap", "27");
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;
            
            public Panel getTestPanel(String panelId) {
                return new ParamPanel(panelId);
            }
        });
        tester.assertLabel("panel:bindings:0:key", "aap");
        tester.assertLabel("panel:bindings:0:value", "27");        
    }

    private void addInitParameter(WicketTester tester, String key, String value) {
        MockServletContext context = 
            (MockServletContext) tester.getServletSession().getServletContext();
        context.addInitParameter(key, value);
    }
}