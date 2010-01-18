package nl.xs4all.banaan.tst8.web.onchange;


import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.value.ValueMap;
import org.junit.Before;
import org.junit.Test;

public class OnchangePanelTest {
    private MockInjector injector;
    private WicketTester tester;
    private ValueMap map;                   // model for the form

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
        map = OnchangePanel.makeModel();
        tester.startPanel(new TestPanelSource() {
            private static final long serialVersionUID = 1L;
            
            public Panel getTestPanel(String panelId) {
                return new OnchangePanel(panelId, new CompoundPropertyModel<ValueMap>(map));
            }
        });
    }
    
    @Test
    public void testSubmitButtonWorks() {
        final FormTester formTester = tester.newFormTester("panel:form");
        formTester.submit();
        assertEquals("true", map.get("submitSeen"));
    }
    
    //
    // To test an AJAX onchange event, fill values using formtester,
    // then dont submit, but execute an ajax event.
    //
    @Test
    public void testOnchangeGetsInvoked() {
        final FormTester formTester = tester.newFormTester("panel:form");
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
        final FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode2", "");
        tester.executeAjaxEvent("panel:form:zipcode2", "onchange");
        assertEquals(null, map.get("changeSeen"));
        assertEquals("", map.get("street"));
        assertEquals("inZip2", map.get("errorSeen"));        
    }  

    @Test
    public void testOnchangeWillHappenIfRequiredIsMet() {
        final FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode2", "yes");
        tester.executeAjaxEvent("panel:form:zipcode2", "onchange");
        assertEquals("true", map.get("changeSeen"));
        assertEquals("prefill2", map.get("street"));
        assertEquals(null, map.get("errorSeen"));
    } 
    
    @Test
    public void testOnchangeWontHappenIfValidationFails() {
        final FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode3", "bad");
        tester.executeAjaxEvent("panel:form:zipcode3", "onchange");
        assertEquals(null, map.get("changeSeen"));
        assertEquals("that was an error", map.get("street"));
        assertEquals("inZip3", map.get("errorSeen"));
    }
    
    @Test
    public void testOnchangeWillHappenIfValidationPasses() {
        final FormTester formTester = tester.newFormTester("panel:form");
        formTester.setValue("zipcode3", "999");
        tester.executeAjaxEvent("panel:form:zipcode3", "onchange");
        assertEquals("true", map.get("changeSeen"));
        assertEquals("prefill3", map.get("street"));
        assertEquals(null, map.get("errorSeen"));
    } 
}
