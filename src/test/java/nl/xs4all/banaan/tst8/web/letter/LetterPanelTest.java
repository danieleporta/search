package nl.xs4all.banaan.tst8.web.letter;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.LETTER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.POSTAGE1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET2;
import nl.xs4all.banaan.tst8.domain.Letter;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;


public class LetterPanelTest {
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
    
    private static class LetterModel extends LoadableDetachableModel<Letter> {
        private static final long serialVersionUID = 1L;
        @Override
        public Letter load() {
            return LETTER1;
        }
    }
}
