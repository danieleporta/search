package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;

/**
 * Display key/value pairs in a property collection.
 * hardwired to display only system properties.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    public PropertyPage(final PageParameters parameters) {
        super (parameters);
        PropertyList propertyList = new PropertyList(System.getProperties());
        add(new PropertyPanel("properties", propertyList.getList()));
    }
}
