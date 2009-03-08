package nl.xs4all.banaan.tst8.web.form;

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
        checkEvents(true, false, false, false, false, false);
    }
    
    /** standard submit sequence with validation error */
    @Test
    public void testFormPanelError() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        checkEvents(false, true, false, false, false, false);
    }

    @Test
    public void testFormPanelClickSubmitButton() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("text", "required");
        formTester.submit("button1");
        checkEvents(true, false, true, false, false, false);
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
        checkEvents(true, false, false, true, false, false);
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
        checkEvents(false, false, false, false, true, false);
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
        checkEvents(false, false, false, false, false, true);
    }
    
    /**
     * without defaultFromProcessing,show absence of validation.
     */
    @Test
    public void testFormPanelClickButtonWithoutDefaultFormProcessingDoesNoValidation() {
        makeTester();
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit("button4");
        checkEvents(false, false, false, false, false, true);
    }  
    
    
    private String convert(Boolean val) {
        return val ? "true" : "false";
    }
    
    private void checkEvents(Boolean submit, Boolean error, 
            Boolean button1, Boolean button2, Boolean button3, Boolean button4) {
        tester.assertModelValue("panel:form:submitSeen", convert(submit));
        tester.assertModelValue("panel:form:errorSeen", convert(error));
        tester.assertModelValue("panel:form:button1Seen", convert(button1));        
        tester.assertModelValue("panel:form:button2Seen", convert(button2));
        tester.assertModelValue("panel:form:button3Seen", convert(button3));
        tester.assertModelValue("panel:form:button4Seen", convert(button4));
    }    
    
    
    /** create FormPanel to be tested */
    private void makeTester() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new FormPanel(panelId,
                        new CompoundPropertyModel(
                                new ValueMap("text=" 
                                        + ",submitSeen=false"
                                        + ",errorSeen=false"
                                        + ",button1Seen=false"
                                        + ",button2Seen=false"
                                        + ",button3Seen=false"
                                        + ",button4Seen=false"
                                        )));
            }
        });
    }
}
