package nl.xs4all.banaan.tst8.fixtures;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;

/**
 * Wicket tester with some extra assertions applicable to
 * BasePage objects.
 * @author konijn
 *
 */
public class BasePageTester extends WicketTester {
    
    public BasePageTester (DemoApplication application) {
        super(application);
    }

    /**
     * check that a rendered subclass of basepage has expected class 
     * and contents
     */
    public void checkBasePage(Class<? extends BasePage> pageClass, String ... patterns) {
        assertRenderedPage(pageClass);
        
        //assert rendered label components that any basepage has
        assertComponent("feedback", FeedbackPanel.class);
        assertComponent("menu", MenuPanel.class);
        assertLabel("buildinfo:name", BUILD_NAME1);
        
        for (String pattern : patterns) {
            assertContains(pattern);
        }
    }

}
