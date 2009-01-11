package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.web.home.HomePage;

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
    public void setUp() {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderMyPage() {
        tester.startPage(HomePage.class);
        fixtures.checkBasePage(HomePage.class, "This shows");

    }
}
