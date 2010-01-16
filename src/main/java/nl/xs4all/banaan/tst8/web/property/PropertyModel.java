package nl.xs4all.banaan.tst8.web.property;

import java.util.List;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.util.Either;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Model that provides access to a list of properties in resource at given location.
 */
class PropertyModel extends LoadableDetachableModel<Either<List<Assoc<String>>,String>> {
    private static final long serialVersionUID = 1L;

    private final PropertyReader propertyReader;
    private final String location;

    
    public PropertyModel(PropertyReader propertyReader, String location) {
        this.location = location;
        this.propertyReader = propertyReader;
    }
    
    @Override
    public Either<List<Assoc<String>>,String> load() {
        try {
            return Either.good(propertyReader.read(location));
        } catch (ServiceException se) {
            return Either.bad(se.getMessage());
        }
    }
}
