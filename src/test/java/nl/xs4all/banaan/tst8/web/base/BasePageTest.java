package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.fixtures.ThrowingPage;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.ITestPageSource;
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
        fixtures = Fixtures.get();
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

 

    @Test
    public void testServiceError() {
        fixtures.checkServiceException (new ITestPageSource () {
            public Page getTestPage (){
                return new ThrowingPage(new ServiceException ("oops"));   
            }
        },
        "oops");
    }
        
}
