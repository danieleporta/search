package nl.xs4all.banaan.tst8.web.property;

import java.util.List;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Model that provides access to a list of properties in resource at given location.
 */
class PropertyModel extends LoadableDetachableModel<List<Assoc<String>>> {
    private static final long serialVersionUID = 1L;

    private final PropertyReader propertyReader;
    private final String location;

    
    public PropertyModel(PropertyReader propertyReader, String location) {
        this.location = location;
        this.propertyReader = propertyReader;
    }
    
    @Override
    public List<Assoc<String>> load() {
        try {
            //  TODO: find a clean way to show an alternative panel
            // if no properties are found at location.
            return propertyReader.read(location);
        } catch (ServiceException se) {
            throw new RuntimeException(se);
        }
    }
}