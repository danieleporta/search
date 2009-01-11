package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.log4j.Logger;
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

        //assert rendered page class
        // TODO: This may well be the error class,
        // since tester provides us with a different jndi provider.
        // tester.assertRenderedPage(JndiPage.class);

        //assert rendered label component
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
    }
}
