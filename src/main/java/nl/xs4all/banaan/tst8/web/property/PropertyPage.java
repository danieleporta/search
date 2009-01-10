package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;



public class PropertyPage extends BasePage {
    public PropertyPage(final PageParameters parameters) {
        super (parameters);
        PropertyList propertyList = new PropertyList(System.getProperties());
        add(new PropertyPanel("properties", propertyList.getList()));
    }
}
