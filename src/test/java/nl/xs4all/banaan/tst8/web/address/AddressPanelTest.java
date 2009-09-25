package nl.xs4all.banaan.tst8.web.address;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test component page: a demo of nested compound property model use.
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class AddressPanelTest {
    @Resource
    private DemoApplication demoApplication;
    
    private BasePageTester tester;

    @Before
    public void setUp() {
        tester = new BasePageTester(demoApplication);
    }
    
    @Test
    public void testAddressPanel() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new AddressPanel(panelId, new AddressModel());
            }
        });
        tester.assertLabel("panel:name", NAME1);
        tester.assertLabel("panel:street", STREET1);
        tester.assertLabel("panel:city", CITY1);
    }
    
    private static class AddressModel extends LoadableDetachableModel {
        private static final long serialVersionUID = 1L;

        @Override
        protected Object load() {
            return ADDRESS1;
        }
    }

}
