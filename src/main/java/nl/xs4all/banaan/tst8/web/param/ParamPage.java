package nl.xs4all.banaan.tst8.web.param;

import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;

/**
 * Show context params as provided by servlet context. 
 * @author konijn
 *
 */
public class ParamPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public ParamPage(final PageParameters parameters) {
        super (parameters);
        add(new ParamPanel("params"));
    }
}
