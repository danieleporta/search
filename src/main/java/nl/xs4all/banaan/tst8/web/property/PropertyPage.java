package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;


/**
 * Display key/value pairs in a property collection.
 * hardwired to display only system properties.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    public PropertyPage() {
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        PropertyList propertyList = 
            DemoApplication.get().getPropertyReader().read(null);
        add(new PropertyPanel("properties", propertyList.getList()));
    }
}
