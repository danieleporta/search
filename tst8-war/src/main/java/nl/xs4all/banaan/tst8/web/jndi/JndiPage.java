package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;

/** Shows result of a JNDI lookup. */
public class JndiPage extends BasePage {

    public JndiPage(PageParameters pageParameters) {
        this(pageParameters.getString("location", ""));
    }
    
    public JndiPage() {
        this("");
    }
    
    public JndiPage(final String location) {
        add(new JndiPanel("jndi", location));
    }
}
