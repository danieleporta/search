package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;
import nl.xs4all.banaan.tst8.fixtures.ThrowingPage;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.letter.LetterPage;
import nl.xs4all.banaan.tst8.web.notificator.NotificatorPage;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePage;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.ITestPageSource;
import org.junit.Test;

/**
 * Verify that the links provided by the base page are clickable
 * and lead to expected type of destination.
 * @author konijn
 *
 */
public class BasePageTest extends SpringJUnitWicketTest {

    @Test
    public void testClickLink1() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:0:value");
        tester.checkBasePage(HomePage.class);
    }
    
    @Test
    public void testClickLink2() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:1:value");
        tester.checkBasePage(JndiPage.class);
    }
    
    @Test
    public void testClickLink3() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:2:value");
        tester.checkBasePage(LetterPage.class);
    }
    
    @Test
    public void testClickLink4() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:3:value");
        tester.checkBasePage(NotificatorPage.class);
    }

    @Test
    public void testClickLink5() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:4:value");
        tester.checkBasePage(OnchangePage.class);
    }
    
    @Test
    public void testClickLink6() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:5:value");
        tester.checkBasePage(ParamPage.class);
    }
    
    @Test
    public void testClickLink7() {
        tester.startPage(HomePage.class);
        tester.clickLink("menu:bindings:6:value");
        tester.checkBasePage(PropertyPage.class);
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
