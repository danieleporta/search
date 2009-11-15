package nl.xs4all.banaan.tst8.web.address;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;
import nl.xs4all.banaan.tst8.domain.Address;
import nl.xs4all.banaan.tst8.fixtures.InjectedWicketTest;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Test;

/**
 * Test component page: a demo of nested compound property model use.
 * @author konijn
 *
 */
public class AddressPanelTest extends InjectedWicketTest {
    
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
    
    private static class AddressModel extends LoadableDetachableModel<Address> {
        private static final long serialVersionUID = 1L;

        @Override
        protected Address load() {
            return ADDRESS1;
        }
    }

}
