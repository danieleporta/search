package nl.xs4all.banaan.tst8.web.property;

import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.ITestPageSource;
import org.junit.Test;

/**
 * verify that expected points show up on property page
 * @author konijn
 *
 */
public class PropertyPageTest extends SpringJUnitWicketTest {
    
    /**
     * default page shows system properties.
     */
    @Test
    public void testRenderPropertyPage1() {
        tester.startPage(PropertyPage.class);
        tester.checkBasePage(PropertyPage.class, "Properties", "user.name");
    }

    /**
     * access property file
     */
    @Test
    public void testRenderPropertyPage2() {
        tester.startPage(new PropertyPage("/build.properties"));
        tester.checkBasePage(PropertyPage.class, "Properties", "group");
    }
    
    @Test
    public void testServiceError() {
        tester.checkServiceException (new ITestPageSource () {
            private static final long serialVersionUID = 1L;

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
        tester.checkBasePage(PropertyPage.class, "Properties", "user.name");
    } 
    
    @Test
    public void testRenderPropertyViaPageParam2() {
        tester.startPage(new PropertyPage(new PageParameters ("location=")));
        tester.checkBasePage(PropertyPage.class, "Properties", "user.name");
    }
    
    @Test
    public void testRenderPropertyViaPageParam3() {
        tester.startPage(new PropertyPage(
                new PageParameters ("location=/build.properties")));
        tester.checkBasePage(PropertyPage.class, "Properties", "group");
    }
    
    /**
     * There should be a form with a field and a button;
     * used to select what property file to display.
     */
    @Test
    public void testFormContents () {
        tester.startPage(PropertyPage.class);
        FormTester formTester = tester.newFormTester("form");
        assertEquals("", formTester.getTextComponentValue("field"));
        formTester.setValue("field", "/build.properties");
        formTester.submit("confirm");
        tester.checkBasePage(PropertyPage.class, "group");
    }
}
