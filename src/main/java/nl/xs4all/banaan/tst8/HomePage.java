package nl.xs4all.banaan.tst8;

import org.apache.wicket.PageParameters;

/**
 * Homepage
 */
public class HomePage extends BasePage {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public HomePage(final PageParameters parameters) {
        super (parameters);
        add(new ParamPanel("params"));
        add(new JndiPanel("jndi"));
        add(new PropertyPanel("system", 
                PropertyBinding.bindingList(System.getProperties())));
        getSession().info("Hello, this is feedback");
    }
}
