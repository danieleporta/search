package nl.xs4all.banaan.tst8.web.onchange;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;

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
        ValueMap map = new ValueMap("zipcode=,zipcode2=junk,zipcode3=123,street=,submitSeen=false");
        add (new OnchangePanel("onchange", new CompoundPropertyModel(map)));
    }
}
