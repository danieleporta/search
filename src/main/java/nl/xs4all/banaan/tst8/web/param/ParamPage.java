package nl.xs4all.banaan.tst8.web.param;

import nl.xs4all.banaan.tst8.web.base.BasePage;

/**
 * Show context params as provided by servlet context. 
 * @author konijn
 *
 */
public class ParamPage extends BasePage {
    public ParamPage() {
        add(new ParamPanel("params"));
    }
}
