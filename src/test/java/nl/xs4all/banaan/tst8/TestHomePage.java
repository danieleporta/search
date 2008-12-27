package nl.xs4all.banaan.tst8;

import junit.framework.TestCase;
import nl.xs4all.banaan.tst8.home.HomePage;
import nl.xs4all.banaan.tst8.property.PropertyPanel;

import org.apache.wicket.util.tester.WicketTester;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase
{
	private WicketTester tester;

	public void setUp()
	{
	    WicketApplication app = new WicketApplication();
		tester = new WicketTester(app);
	}

	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		//assert rendered label component
		tester.assertComponent("system", PropertyPanel.class);
	}
}
