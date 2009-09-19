package nl.xs4all.banaan.tst8.web.upload;


import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
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

}
