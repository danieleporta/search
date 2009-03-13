package nl.xs4all.banaan.tst8.web.form;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.value.ValueMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Experiment with testing buttons on a form.
 * Whereas for links there is a clickLink() that can test
 * with or without Ajax, such a simple interface is lacking for buttons.
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class FormPanelTest {
    
    @Resource
    private BasePageTester tester;
    
    /** show a form, don't push any buttons */
    @Test
    public void testPanelShowing() {
        makeTester();
        tester.assertModelValue("panel:form:text", "");
    }

    /** standard submit sequence */
    @Test
    public void testFormPanelSubmit() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "required");
        formTester.submit();
        checkEvents("submitSeen");
    }
    
    /** standard submit sequence with validation error */
    @Test
    public void testFormPanelError() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        checkEvents("errorSeen");
    }

    @Test
    public void testFormPanelClickSubmitButton() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "required");
        formTester.submit("button1");
        checkEvents("submitSeen", "button1Seen");
    }
    
    /** 
     * a button, marked up as "button" rather than "submit"
     * will submit the form just like an input of type submit.
     */
    @Test
    public void testFormPanelClickNonSubmitButton() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "required");
        formTester.submit("button2");
        checkEvents("submitSeen", "button2Seen");
    }
    
    /** 
     * the simplest way to avoid onSubmit() is to make a button
     * markup with a Link component behind it.  Notes:
     * <ul>
     * <li> this is tested with clickLink() rather than submit().
     * <li> this bypasses validation
     * </ul> 
     */
    @Test
    public void testFormPanelClickButtonWithOnClickEvenIfNoValidation() {
        makeTester();
        tester.clickLink("panel:form:button3");
        checkEvents("button3Seen");
    }

    /**
     * without defaultFromProcessing, the form onSubmit() is not called,
     * fields are not updated and there is no validation.
     */
    @Test
    public void testFormPanelClickButtonWithoutDefaultFormProcessingDoesNoFieldUpdate() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "required");
        formTester.submit("button4");
        tester.assertModelValue("panel:form:text", "");
        checkEvents("button4Seen");
    }
    
    /**
     * without defaultFromProcessing, show absence of validation.
     */
    @Test
    public void testFormPanelClickButtonWithoutDefaultFormProcessingDoesNoValidation() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit("button4");
        checkEvents("button4Seen");
    }  
    
    // minimal test: no verification of button onSubmit() yet.
    @Test
    public void testFormPanelClickAjaxButtonWithJavascriptEnabledAndValidationError() {
        makeTester();
        tester.clickLink("panel:button5", true);
        // dont update text field, triggering onError()
        checkEvents("errorSeen");
    }
    
    private String[] allEvents = {"submitSeen", "errorSeen",
            "button1Seen", "button2Seen", "button3Seen", "button4Seen"};
    
    private void checkEvents(String... expectedEvents) {
        Set<String> set = new HashSet<String>();
        for (String event : expectedEvents) {
            set.add(event);
        }

        for (String key: allEvents) {
            tester.assertModelValue("panel:form:" + key,
                    set.contains(key) ? "true" : "false"); 
        }
    }
    
    /** create FormPanel to be tested */
    private void makeTester() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                ValueMap map = new ValueMap("text=");
                for (String key: allEvents) {
                    map.add(key, "false");
                }
                return new FormPanel(panelId,
                        new CompoundPropertyModel(map));
            }
        });
    }
}
