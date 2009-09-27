package nl.xs4all.banaan.tst8.web.onchange;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

/**
 * Show how entering data in field A can change content
 * of field B, using AJAX.
 * @author konijn
 *
 */
public class OnchangePage extends BasePage {
    public OnchangePage() {
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        add (new OnchangePanel("onchange"));
    }
}
