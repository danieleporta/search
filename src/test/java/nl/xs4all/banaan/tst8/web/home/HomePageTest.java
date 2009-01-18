package nl.xs4all.banaan.tst8.web.home;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.Fixtures;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class HomePageTest {
    private Fixtures fixtures;
    private BasePageTester tester;

    @Before
    public void setUp() {
        fixtures = Fixtures.get();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderMyPage() {
        tester.startPage(HomePage.class);
        tester.checkBasePage(HomePage.class, "This shows");

    }
}
