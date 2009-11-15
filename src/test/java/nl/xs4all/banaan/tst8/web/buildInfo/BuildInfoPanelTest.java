package nl.xs4all.banaan.tst8.web.buildInfo;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_GROUP1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_USER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_VERSION1;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Test rendering the build Info in a panel.
 * @author konijn
 *
 */

public class BuildInfoPanelTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
    @Test
    public void testBuildInfoPanel() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new BuildInfoPanel(panelId);
            }
        });
        
        tester.assertLabel("panel:name", BUILD_NAME1);
        tester.assertLabel("panel:group", BUILD_GROUP1);
        tester.assertLabel("panel:version", BUILD_VERSION1);
        tester.assertLabel("panel:user", BUILD_USER1);
    }
}
