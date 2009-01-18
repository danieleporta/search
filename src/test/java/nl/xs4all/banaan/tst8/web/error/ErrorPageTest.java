package nl.xs4all.banaan.tst8.web.error;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.Fixtures;

import org.junit.Before;
import org.junit.Test;

public class ErrorPageTest {
    private Fixtures fixtures;
    private BasePageTester tester;

    @Before
    public void setUp() {
        fixtures = Fixtures.get();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderErrorPage() {
        tester.startPage(ErrorPage.class);
        tester.checkBasePage(ErrorPage.class, "could not be completed");
    }
}
