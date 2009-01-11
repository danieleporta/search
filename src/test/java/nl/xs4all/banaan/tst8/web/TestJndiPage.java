package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestJndiPage {
    private Fixtures fixtures;
    private WicketTester tester;

    @Before
    public void setUp()
    {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderJndiPage()
    {
        //start and render the test page
        tester.startPage(JndiPage.class);

        checkComponents();
        
        // assert rendered data from fixture
        tester.assertContains("aapval");
    }
   
    /**
     * Any rendered JndiPage should have feedback and menu panel
     */
    private void checkComponents() {
        //assert rendered page class
        tester.assertRenderedPage(JndiPage.class);

        //assert rendered label component
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
    }
    
}
