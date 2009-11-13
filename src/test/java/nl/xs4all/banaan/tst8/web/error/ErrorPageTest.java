package nl.xs4all.banaan.tst8.web.error;

import nl.xs4all.banaan.tst8.fixtures.InjectedWicketTest;

import org.junit.Test;

public class ErrorPageTest extends InjectedWicketTest {

    @Test
    public void testRenderErrorPage() {
        tester.startPage(ErrorPage.class);
        tester.checkBasePage(ErrorPage.class, "could not be completed");
    }
}
