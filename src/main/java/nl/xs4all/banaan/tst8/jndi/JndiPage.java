package nl.xs4all.banaan.tst8.jndi;

import nl.xs4all.banaan.tst8.base.BasePage;

import org.apache.wicket.PageParameters;

public class JndiPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public JndiPage(final PageParameters parameters) {
        super (parameters);
        add(new JndiPanel("jndi"));
    }
}
