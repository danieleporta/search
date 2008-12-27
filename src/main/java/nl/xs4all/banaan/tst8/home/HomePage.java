package nl.xs4all.banaan.tst8.home;

import nl.xs4all.banaan.tst8.base.BasePage;
import nl.xs4all.banaan.tst8.jndi.JndiPage;
import nl.xs4all.banaan.tst8.param.ParamPage;
import nl.xs4all.banaan.tst8.property.PropertyPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;


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
        add(new BookmarkablePageLink("paramLink", ParamPage.class));
        add(new BookmarkablePageLink("jndiLink", JndiPage.class));
        add(new BookmarkablePageLink("propertyLink", PropertyPage.class));
    }
}
