package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.ThrowingPage;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPage;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.ITestPageSource;
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

    @Test
    public void testServiceError() {
        tester.checkServiceException (new ITestPageSource () {
            private static final long serialVersionUID = -6384361110224898899L;

            public Page getTestPage (){
                return new ThrowingPage(new ServiceException ("oops"));   
            }
        },
        "oops");
    }
        
}
