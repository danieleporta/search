package nl.xs4all.banaan.tst8.web.buildInfo;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_GROUP1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_USER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_VERSION1;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test rendering the build Info n a panel.
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class BuildInfoPanelTest {
    
    @Resource
    private BasePageTester tester;
    
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
