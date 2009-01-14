package nl.xs4all.banaan.tst8.web.base;


import static org.junit.Assert.*;
import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify that the links provided by the base page are clickable
 * and lead to expected type of destination.
 * @author konijn
 *
 */
public class BasePageTest {
    private Fixtures fixtures;
    private WicketTester tester;

    @Before
    public void setUp() {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    @Test
    public void testClickLink1() {
        tester.startPage(HomePage.class);
        // tester.debugComponentTrees();
        tester.clickLink("menu:bindings:0:value");
        fixtures.checkBasePage(HomePage.class);
    }
    
    @Test
    public void testClickLink2() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:1:value");
        fixtures.checkBasePage(JndiPage.class);
    }
    
    @Test
    public void testClickLink3() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:2:value");
        fixtures.checkBasePage(ParamPage.class);
    }
    
    @Test
    public void testClickLink4() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:3:value");
        fixtures.checkBasePage(PropertyPage.class);
    }

    /**
     * Helper to cause an exception.
     * Our error handling model:
     *  -- services can raise exception;
     *  -- basepage web layer logs this
     *  -- and reraises exception;
     *  -- now application shows error page.
     *
     * Note that basepage cannot just set repsonse page;
     * you need an exception to bypass the enclosing blocks
     * and avoid errors in rendering there because the service
     * did not provide a workable model.
     * 
     * An alternative would be to override newWebRequestCycle
     * in the application, and in onRuntimeException provide
     * an error page.
     *  
     * @author konijn
     *
     */
    private static class ThrowingPage extends BasePage {
        ServiceException exception;
        public ThrowingPage (ServiceException exception) {
            this.exception = exception;
            init();
        }
        
        @Override
        public void doInit () throws ServiceException {
            if (exception != null) {
                throw exception;
            }
            throw new RuntimeException ("gotcha!");
        }
    }
    
    // @Test
    public void testError() {
        try {
            tester.startPage(new ThrowingPage(null));
            fail ("Exception expected");
        } catch (RuntimeException e) {
            // OK
        }
    }

    @Test
    public void testServiceError() {
        try {
            tester.startPage(new ThrowingPage(null));
            fail ("Exception expected");
        } catch (RuntimeException e) {
            // no action
        }
    }
        
}
