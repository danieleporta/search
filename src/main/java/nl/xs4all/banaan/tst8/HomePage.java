package nl.xs4all.banaan.tst8;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * Homepage
 */
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public HomePage(final PageParameters parameters) {
        
        // Add the simplest type of label
        add(new Label("message",
                "If you see this message wicket is properly configured and running"));
        add(new FeedbackPanel("feedback"));
        add(new JndiPanel("jndi"));
        add(new PropertyPanel("system", 
                Binding.bindingList(System.getProperties())));
        getSession().info("Hello, this is feedback");
    }
}
