package nl.xs4all.banaan.tst8.web.property;

import java.util.List;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.log4j.Logger;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Model that provides access to a list of properties in resource at given location.
 */
class PropertyModel extends LoadableDetachableModel<List<Assoc<String>>> {
    private static final long serialVersionUID = 1L;

    private final PropertyReader propertyReader;
    // private final Logger logger;
    private final String location;

    
    public PropertyModel(PropertyReader propertyReader, Logger logger, String location) {
        
        this.location = location;
        this.propertyReader = propertyReader;
        // this.logger = logger;
    }
    
    @Override
    public List<Assoc<String>> load() {
        try {
            //  TODO: find a clean way to show an alternative panel
            // if no properties are found at location.
            return propertyReader.read(location).getList();
        } catch (ServiceException se) {
            
            // can't do this because not serialisable.
            // logger.error("Caught Service Exception", se);
            throw new RuntimeException(se);
        }
    }
}