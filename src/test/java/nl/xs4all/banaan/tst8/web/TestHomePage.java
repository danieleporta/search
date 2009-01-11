package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.web.home.HomePage;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
    	private Fixtures fixtures;
	private WicketTester tester;

	@Before
	public void setUp()
	{
	        fixtures = new Fixtures();
	        tester = fixtures.getTester();
	}

	@Test
	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		//assert rendered label component
		tester.assertComponent("feedback", FeedbackPanel.class);
	}
}
