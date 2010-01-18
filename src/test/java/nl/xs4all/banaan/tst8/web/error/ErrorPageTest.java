package nl.xs4all.banaan.tst8.web.error;

import nl.xs4all.banaan.tst8.fixtures.MockInjector;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class ErrorPageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjector();
        tester = injector.get(WicketTester.class);
    }

    @Test
    public void testRenderErrorPage() {
        tester.startPage(ErrorPage.class);
        tester.assertContains("could not be completed");
    }
}
