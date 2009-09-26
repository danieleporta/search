package nl.xs4all.banaan.tst8.web.onchange;


import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.value.ValueMap;
import org.junit.Before;
import org.junit.Test;

public class OnchangePanelTest extends SpringJUnitWicketTest {
    private ValueMap map;                   // model for the form

    @Before @Override
    public void setUp() {
        super.setUp();
        map = new ValueMap("zipcode2=junk");
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;
            
            public Panel getTestPanel(String panelId) {
                return new OnchangePanel(panelId, new CompoundPropertyModel(map));
            }
        });
    }
    
    @Test
    public void testSubmitButtonWorks() {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
    }
    
    //
    // To test an AJAX onchange event, fill values using formtester,
    // then dont submit, but execute an ajax event.
    //
    @Test
    public void testOnchangeGetsInvoked() {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode", "aap");
        tester.executeAjaxEvent("panel:form:zipcode", "onchange");
        assertEquals("true", map.get("changeSeen"));
        assertEquals("aap", map.get("zipcode"));
        assertEquals("prefill", map.get("street"));
    }
    
    //
    // To test an AJAX onchange event, fill values using formtester,
    // then dont submit, but execute an ajax event.
    //
    @Test
    public void testOnchangeWontHappenIfRequiredNotMet() {
        FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode2", "");
        tester.executeAjaxEvent("panel:form:zipcode2", "onchange");
        assertEquals(null, map.get("changeSeen"));
        assertEquals(null, map.get("street"));
    }  

}
