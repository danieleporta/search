package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;


/**
 * Display key/value pairs in a property collection.
 * takes a param to determine what property resource is shown.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    private String location;
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public PropertyPage() {
        setLocation("");
        init();
    }
    
    public PropertyPage(String location) {
        setLocation(location);
        init();
    }

    public PropertyPage(PageParameters parameters) {
        setLocation(parameters.getString("location", ""));
        init();
    }

    @Override
    public void doInit() throws ServiceException {
        add(new PropertyPanel("properties", location));
    }
}
