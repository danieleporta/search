package nl.xs4all.banaan.tst8.web.address;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.After;
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
    private BasePageTester tester;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testAddressPanel() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new AddressPanel(panelId, new AddressModel());
            }
        });
        tester.assertComponent("panel:name", Label.class);
        tester.assertComponent("panel:street", Label.class);
        tester.assertComponent("panel:city", Label.class);
        tester.assertContains(NAME1);
        tester.assertContains(STREET1);
        tester.assertContains(CITY1);
    }
    
    private static class AddressModel implements IModel {
        private static final long serialVersionUID = 1L;

        public Object getObject() {
            return ADDRESS1;
        }

        public void setObject(Object object) {
        }

        public void detach() {
        }
    }

}
