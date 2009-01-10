package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.web.WicketApplication;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestErrorPage {
    private WicketTester tester;

    @Before
    public void setUp()
    {
        WicketApplication app = new WicketApplication();
        tester = new WicketTester(app);
    }

    @Test
    public void testRenderErrorPage()
    {
        //start and render the test page
        tester.startPage(ErrorPage.class);

        //assert rendered page class
        tester.assertRenderedPage(ErrorPage.class);

        //assert rendered label component
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
    }
}
