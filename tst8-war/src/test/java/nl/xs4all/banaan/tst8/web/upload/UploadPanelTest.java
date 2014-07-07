package nl.xs4all.banaan.tst8.web.upload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.value.ValueMap;
import org.junit.Before;
import org.junit.Test;


/** Experiment with file upload on a form. */
public class UploadPanelTest  {
    private MockInjector injector;
    private WicketTester tester;
    private ValueMap map;                   // model for the form

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
        map = new ValueMap("text=x");
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;
            
            public Panel getTestPanel(String panelId) {
                return new UploadPanel(panelId, new CompoundPropertyModel<ValueMap>(map));
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
    public void testThatFileContentIsAccessible() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setFile("file", getResourceAsFile("/sample.txt"), "text/plain");
        formTester.submit();
        assertEquals("sample text file for upload testing\n", map.get("fileContents"));
    }
    
    @Test
    public void testTextFieldAcceptsInputAfterSubmitIfYouAlsoHaveFile() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "aap");
        formTester.setFile("file", getResourceAsFile("/sample.txt"), "text/plain");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        assertEquals("aap", map.get("text"));
        assertEquals("aap", map.get("textSeen"));
    }
    
    @Test
    public void testTextFieldWithoutSetfileWasBrokenButFixedIn143() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "aap");
        //
        // Without setFile there is no file in the request,
        // but it's still multipart.  This causes wicket to ignore the text fields.
        // With real browsers, that does not happen, since they send a file
        // with no name and empty content rather than omitting the field altogether
        // from the reply.  Like so:
        //
        //    -----------------------------193884167618819539251897464716
        //    Content-Disposition: form-data; name="file"; filename=""
        //    Content-Type: application/octet-stream
        //
        //
        //    -----------------------------193884167618819539251897464716
        //    Content-Disposition: form-data; name="text"
        //
        //    aa
        //    -----------------------------193884167618819539251897464716--
        //
        // Thus, we need a way to add empty file to the request.
        //
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        // In 1.3: assertEquals(null, map.get("text"));
        assertEquals("aap", map.get("text"));
    }

    @Test
    public void testTextFieldWasAlsoBrokenWithEmptyFileFieldButFixedIn143() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "aap");
        // try simply setting an empty field
        formTester.setValue("file", "");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        // good: it does not show as file upload
        assertEquals(false, map.get("haveUpload"));
        // bad: but it still breaks other text fields.
        // In 1.3: assertEquals(null, map.get("text"));
        assertEquals("aap", map.get("text"));
    }
    
    @Test
    public void testTextFieldWithFileThatHasNoContentAndNoName() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "aap");
        formTester.setFile("file", getEmptyFile(), "application/octet-stream");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
        // good: it does not show as file upload
        assertEquals(false, map.get("haveUpload"));
        // also good: the text field is no longer broken.
        assertEquals("aap", map.get("text"));
    }
    
    @Test
    public void testThatResourceCanBeProvidedViaTester() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        // this would allow testing clients that talk to non-existing fields.
        tester.getServletRequest().addFile("file", getResourceAsFile("/sample.txt"), "image/png");
        formTester.submit();
        assertEquals(true, map.get("haveUpload"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testThatSetFileDoesNotAcceptNull() throws URISyntaxException {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setFile("file", null, null);
        fail("Null resource when setting file went undetected");
    }
    
    @Test
    public void testThatYouCannotSimplyUseOddNamesForAttachements() throws URISyntaxException {
        File file = getResourceAsFile("/sample.txt");
        boolean canRename = file.renameTo(new File("/noot.txt"));
        assertEquals(false, canRename);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testThatResourcesThatDontExistCauseAnException() throws URISyntaxException {
        getResourceAsFile("/i-dont-exist.txt");
    }
    
    private File getResourceAsFile(String resourceName) throws URISyntaxException {
        URL resource = getClass().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("No such resource: " + resourceName);
        }
        return new File(new URI(resource.toString()));
    }
    
    /**
     * An empty file for use in formTester.setFile().
     * @return
     * @throws URISyntaxException
     */
    private File getEmptyFile() throws URISyntaxException {
        String resourceName = "/empty.txt";
        URL resource = getClass().getResource(resourceName );
        if (resource == null) {
            throw new IllegalArgumentException("No such resource: " + resourceName);
        }
        return new File(new URI(resource.toString())) {
            private static final long serialVersionUID = 992382817279158469L;

            @Override
            public String getName() {
                return "";
            }
        };
    }
}