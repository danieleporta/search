package nl.xs4all.banaan.tst8.web.property;

import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

/**
 * verify that expected points show up on property page
 * @author konijn
 *
 */
public class PropertyPageTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
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
        tester.assertInvisible("properties:bad");
        tester.assertVisible("properties:good");
    }
    
    
    @Test()
    public void testServiceError() {
        tester.startPage(new PropertyPage("/not/found/build.properties"));
        tester.assertVisible("properties:bad");
        tester.assertInvisible("properties:good");
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
     * There should be a form with a field;
     * used to select what property file to display.
     */
    @Test
    public void testFormContents () {
        tester.startPage(PropertyPage.class);
        FormTester formTester = tester.newFormTester("properties:form");
        assertEquals("", formTester.getTextComponentValue("field"));
        formTester.setValue("field", "/build.properties");
        formTester.submit();
        tester.checkBasePage(PropertyPage.class, "group");
    }
}
