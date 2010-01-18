package nl.xs4all.banaan.tst8.web.home;

import nl.xs4all.banaan.tst8.fixtures.MockInjector;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class HomePageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjector();
        tester = injector.get(WicketTester.class);
    }

    @Test
    public void testRenderMyPage() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        tester.assertContains("This shows");
    }
}
