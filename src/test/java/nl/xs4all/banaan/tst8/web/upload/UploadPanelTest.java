package nl.xs4all.banaan.tst8.web.upload;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.Resource;
import javax.security.auth.login.FailedLoginException;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.value.ValueMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Experiment with file upload on a form.
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class UploadPanelTest {

    @Resource
    private BasePageTester tester;
    private ValueMap map;

    @Before
    public void setUp() {
        map = new ValueMap("");
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;
            
            public Panel getTestPanel(String panelId) {
                return new UploadPanel(panelId, new CompoundPropertyModel(map));
            }
        });
    }
    
    @Test
    public void testThatThereIsNoUploadOnAnEmptyForm() {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        assertEquals(false, map.get("haveUpload"));        
    }
    
    @Test
    public void testThatResourceCanBeUploaded() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setFile("file", getResourceAsFile("/sample.txt"), "text/plain");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        assertEquals(true, map.get("haveUpload"));
        assertEquals("sample.txt", map.get("fileName"));
        assertEquals(36L, map.get("fileSize"));
        assertEquals("text/plain", map.get("fileType")); 
    }
    
    @Test
    public void testThatResourceTypeIsWhatTheClientSays() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setFile("file", getResourceAsFile("/sample.txt"), "image/png");
        formTester.submit();
        assertEquals("image/png", map.get("fileType")); 
    }
    
    @Test
    public void testThatResourceCanBeProvidedViaTester() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        // this would allow testing clients that talk to non-existing fields.
        tester.getServletRequest().addFile("file", getResourceAsFile("/sample.txt"), "image/png");
        formTester.submit();
        assertEquals(true, map.get("haveUpload"));
    }
    
    @Test
    public void testThatYouCannotSimplyUseOddNamesForAttachements() throws URISyntaxException {
        File file = getResourceAsFile("/sample.txt");
        boolean canRename = file.renameTo(new File("/noot.txt"));
        assertEquals(false, canRename);
    }
    
    @Test
    public void testThatResourcesThatDontExistCauseAnException() throws URISyntaxException {
        try {
            getResourceAsFile("/i-dont-exist.txt");
            fail("missing resource went undetected");
        }
        catch (IllegalArgumentException e){
            // ok
        }
    }
    
    private File getResourceAsFile(String resourceName) throws URISyntaxException {
        URL resource = getClass().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("No such resource: " + resourceName);
        }
        return new File(new URI(resource.toString()));
    }
}
