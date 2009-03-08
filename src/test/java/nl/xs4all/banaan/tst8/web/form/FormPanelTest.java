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
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new FormPanel(panelId,
                        new CompoundPropertyModel(
                                new ValueMap("text=required,submitSeen=false")));
            }
        });
        tester.assertModelValue("panel:form:text", "required");
    }

    /** standard submit sequence */
    @Test
    public void testFormPanelSubmit() {
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;

            public Panel getTestPanel(String panelId) {
                return new FormPanel(panelId,
                        new CompoundPropertyModel(
                                new ValueMap("text=required,submitSeen=false")));
            }
        });

        tester.assertModelValue("panel:form:submitSeen", "false");
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        tester.assertModelValue("panel:form:submitSeen", "true");
    }
    
    
}
