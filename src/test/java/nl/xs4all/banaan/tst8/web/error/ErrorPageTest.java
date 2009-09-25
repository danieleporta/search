package nl.xs4all.banaan.tst8.web.error;

import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;

import org.junit.Test;

public class ErrorPageTest extends SpringJUnitWicketTest {

    @Test
    public void testRenderErrorPage() {
        tester.startPage(ErrorPage.class);
        tester.checkBasePage(ErrorPage.class, "could not be completed");
    }
}
