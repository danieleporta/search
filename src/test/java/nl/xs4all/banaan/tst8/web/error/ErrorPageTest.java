package nl.xs4all.banaan.tst8.web.error;

import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class ErrorPageTest {
    private WicketMockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }


    @Test
    public void testRenderErrorPage() {
        tester.startPage(ErrorPage.class);
        tester.assertContains("could not be completed");
    }
}
