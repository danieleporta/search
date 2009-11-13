package nl.xs4all.banaan.tst8.web.home;

import nl.xs4all.banaan.tst8.fixtures.InjectedWicketTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class HomePageTest extends InjectedWicketTest {

    @Test
    public void testRenderMyPage() {
        tester.startPage(HomePage.class);
        tester.checkBasePage(HomePage.class, "This shows");
    }
}
