package nl.xs4all.banaan.tst8.web.home;

import nl.xs4all.banaan.tst8.fixtures.InjectedWicketTest;

import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class HomePageTest extends InjectedWicketTest {

    @Test
    public void testRenderMyPage() {
        tester.startPage(HomePage.class);
        tester.checkBasePage(HomePage.class, "This shows");
    }
}
