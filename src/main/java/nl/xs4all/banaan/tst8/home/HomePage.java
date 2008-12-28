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

    public HomePage(final PageParameters parameters) {
        super (parameters);
    }
}
