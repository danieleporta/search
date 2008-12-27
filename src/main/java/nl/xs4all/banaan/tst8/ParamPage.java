package nl.xs4all.banaan.tst8;

import org.apache.wicket.PageParameters;

public class ParamPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public ParamPage(final PageParameters parameters) {
        super (parameters);
        add(new ParamPanel("params"));
    }
}
