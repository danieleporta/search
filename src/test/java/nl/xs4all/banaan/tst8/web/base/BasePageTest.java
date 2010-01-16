package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPage;

import org.junit.Before;
import org.junit.Test;

/**
 * Verify at least one of the links on basepage is clickable.
 * and lead to expected type of destination.
 * @author konijn
 *
 */
public class BasePageTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
    @Test
    public void testClickLink0() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:0:value");
        tester.checkBasePage(MenuPage.class);
    }
       
}
