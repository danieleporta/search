package nl.xs4all.banaan.tst8.web.property;

import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;

import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * verify that expected points show up on property page
 * @author konijn
 *
 */
public class PropertyPageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjector();
        tester = injector.get(WicketTester.class);
    }
    
    /** default page shows system properties. */
    @Test
    public void testRenderPropertyPage1() {
        tester.startPage(PropertyPage.class);
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("user.name");
    }

    /** access property file */
    @Test
    public void testRenderPropertyPage2() {
        tester.startPage(new PropertyPage("/build.properties"));
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("group");
        tester.assertVisible("properties:good");
        tester.assertNoErrorMessage();
    }
    
    @Test()
    public void testServiceError() {
        tester.startPage(new PropertyPage("/not/found/build.properties"));
        tester.assertInvisible("properties:good");
        tester.assertErrorMessages(new String[] {"property file not found: /not/found/build.properties"});
    }
        
    /** empty location leads to system properties */
    @Test
    public void testRenderPropertyViaPageParam() {
        tester.startPage(new PropertyPage(new PageParameters ()));
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("user.name");
    } 
    
    @Test
    public void testRenderPropertyViaPageParam2() {
        tester.startPage(new PropertyPage(new PageParameters ("location=")));
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("user.name");
    }
    
    @Test
    public void testRenderPropertyViaPageParam3() {
        tester.startPage(new PropertyPage(
                new PageParameters ("location=/build.properties")));
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("group");
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
        tester.assertRenderedPage(PropertyPage.class);
        tester.assertContains("group");
    }
}
