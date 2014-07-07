package nl.xs4all.banaan.tst8.web.jndi;

import java.util.List;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.util.Either;

import org.apache.wicket.model.LoadableDetachableModel;

/** Model to return list of JNDI bindings at given location */
public class JndiModel extends LoadableDetachableModel<Either<List<Assoc<Object>>,String>> {
    private static final long serialVersionUID = 1L;
    
    // "easiest way to get dependency in model is to pass it from component"
    // http://markmail.org/message/o53vqwilxbnlul5j
    // InjectorHolder is only for spring.
    private final JndiReader jndiReader;
    private final String location; 
    
    public JndiModel(String location, JndiReader jndiReader) {
        this.location = location;
        this.jndiReader = jndiReader;
    }
    
    @Override
    public Either<List<Assoc<Object>>,String> load() {
        try {
            return Either.good(jndiReader.read(location));
        } catch (ServiceException se) {
            return Either.bad(se.getMessage());
        }
    }
}
