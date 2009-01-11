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
    public void setUp() {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderJndiPage1() {
        //start and render the test page
        tester.startPage(JndiPage.class);
        checkContents("aapval");
    }
 
    @Test
    public void testRenderJndiPage2() {
        //start and render the test page
        tester.startPage(new JndiPage());
        checkContents("aapval");
    } 
    
    @Test
    public void testRenderJndiPage3() {
        //start and render the test page
        tester.startPage(new JndiPage(""));
        checkContents("aapval");
    }  
    
    @Test
    public void testRenderJndiPage4() {
        //start and render the test page
        tester.startPage(new JndiPage("jdbc"));
        checkContents("miesval");
    }

    /**
     * It's a JNDI page, with given list of patterns occurring. 
     */
    private void checkContents(String ... patterns) {
        checkJndiPage();
        for (String pattern : patterns) {
            tester.assertContains(pattern);            
        }
    }  
    
    /**
     * Any rendered JndiPage should have feedback and menu panel
     */
    private void checkJndiPage() {
        //assert rendered page class
        tester.assertRenderedPage(JndiPage.class);

        checkBasePage();
    }

    /**
     * These are the components a base page must have
     */
    private void checkBasePage() {
        //assert rendered label component
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
    }
    
}
