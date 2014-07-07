package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPage;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify at least one of the links on basepage is clickable.
 * and lead to expected type of destination.
 * @author konijn
 *
 */
public class BasePageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
    }
    
    @Test
    public void testClickLink0() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:0:value");
        tester.assertRenderedPage(MenuPage.class);
    }
}
