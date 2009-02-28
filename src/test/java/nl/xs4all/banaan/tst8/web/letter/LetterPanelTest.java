package nl.xs4all.banaan.tst8.web.letter;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.LETTER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.POSTAGE1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET2;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class LetterPanelTest {
    @Resource
    private BasePageTester tester;

    @Test
    public void testAddressPanel() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new LetterPanel(panelId, new LetterModel());
            }
        });
        tester.assertLabel("panel:from:name", NAME1);
        tester.assertLabel("panel:from:street", STREET1);
        tester.assertLabel("panel:from:city", CITY1);
        tester.assertLabel("panel:to:name", NAME2);
        tester.assertLabel("panel:to:street", STREET2);
        tester.assertLabel("panel:to:city", CITY2);
        tester.assertLabel("panel:postage", POSTAGE1.toString());    
    }
    
    private static class LetterModel implements IModel {
        private static final long serialVersionUID = 1L;

        public Object getObject() {
            return LETTER1;
        }

        public void setObject(Object object) {
        }

        public void detach() {
        }
    }

}
