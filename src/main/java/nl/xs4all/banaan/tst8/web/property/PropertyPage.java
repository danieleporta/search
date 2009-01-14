package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;

import org.apache.log4j.Logger;

/**
 * Display key/value pairs in a property collection.
 * hardwired to display only system properties.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    private static Logger logger = Logger.getLogger(PropertyPage.class);
    public PropertyPage() {
        try {
            PropertyList propertyList = 
                DemoApplication.get().getPropertyReader().read(null);
            add(new PropertyPanel("properties", propertyList.getList()));
        }
        catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            setResponsePage(ErrorPage.class);
        }
    }
}
