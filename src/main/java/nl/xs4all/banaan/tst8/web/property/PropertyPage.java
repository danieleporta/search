package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;


/**
 * Display key/value pairs in a property collection.
 * takes a param to determine what property resource is shown.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    
    public PropertyPage() {
        this("");
    }

    public PropertyPage(PageParameters parameters) {
        this(parameters.getString("location", ""));
    }
    
    public PropertyPage(String location) {
        add(new PropertyPanel("properties", location));
    }
}
