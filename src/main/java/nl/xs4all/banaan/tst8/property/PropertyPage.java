package nl.xs4all.banaan.tst8.property;

import nl.xs4all.banaan.tst8.PropertyBinding;
import nl.xs4all.banaan.tst8.base.BasePage;

import org.apache.wicket.PageParameters;


public class PropertyPage extends BasePage {
    public PropertyPage(final PageParameters parameters) {
        super (parameters);
        add(new PropertyPanel("properties",
                PropertyBinding.bindingList(System.getProperties())));
    }
}
