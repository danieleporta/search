package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.PropertyReaderImpl;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;

/**
 * Various convenience objects for use during unit testing.
 * @author konijn
 *
 */
public class Fixtures {
    private WicketTester tester;

    public Fixtures () {
        PropertyReader propertyReader = new PropertyReaderImpl();
        JndiReader jndiReader = new JndiReaderFixture();
        DemoApplication app = new DemoApplication();
        tester = new WicketTester(app);
        app.setPropertyReader(propertyReader);
        app.setJndiReader(jndiReader);
    }
    
    public WicketTester getTester() {
        return tester;
    }
    

    /**
     * check that a rendered subclass of basepage has expected class 
     * and contents
     */
    
    public void checkBasePage(Class<? extends BasePage> pageClass, String ... patterns) {
        tester.assertRenderedPage(pageClass);
        
        //assert rendered label components that any basepage has
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
        
        for (String pattern : patterns) {
            tester.assertContains(pattern);
        }
    }
}
