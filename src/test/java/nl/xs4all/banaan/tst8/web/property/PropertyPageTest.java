package nl.xs4all.banaan.tst8.web.property;


import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.ITestPageSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * verify that expected points show up on property page
 * @author konijn
 *
 */
public class PropertyPageTest {
    private Fixtures fixtures;
    private WicketTester tester;

    @Before
    public void setUp() throws Exception {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    /**
     * default page shows system properties.
     */
    @Test
    public void testRenderPropertyPage1() {
        tester.startPage(PropertyPage.class);
        fixtures.checkBasePage(PropertyPage.class, "Properties", "user.name");
    }

    /**
     * access property file
     */
    @Test
    public void testRenderPropertyPage2() {
        tester.startPage(new PropertyPage("/build.properties"));
        fixtures.checkBasePage(PropertyPage.class, "Properties", "group");
    }
    
    @Test
    public void testServiceError() {
        fixtures.checkServiceException (new ITestPageSource () {
            public Page getTestPage (){
                return new PropertyPage("/not/found/build.properties");   
            }
        },
        "not/found");
    }
        
    /**
     * empty location to system properties
     */
    @Test
    public void testRenderPropertyViaPageParam() {
        tester.startPage(new PropertyPage(new PageParameters ()));
        fixtures.checkBasePage(PropertyPage.class, "Properties", "user.name");
    } 
    
    @Test
    public void testRenderPropertyViaPageParam2() {
        tester.startPage(new PropertyPage(new PageParameters ("location=")));
        fixtures.checkBasePage(PropertyPage.class, "Properties", "user.name");
    }
    
    @Test
    public void testRenderPropertyViaPageParam3() {
        tester.startPage(new PropertyPage(
                new PageParameters ("location=/build.properties")));
        fixtures.checkBasePage(PropertyPage.class, "Properties", "group");
    }
}
