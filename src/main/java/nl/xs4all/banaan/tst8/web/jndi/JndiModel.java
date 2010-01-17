package nl.xs4all.banaan.tst8.web.jndi;

import java.util.List;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.log4j.Logger;
import org.apache.wicket.model.LoadableDetachableModel;

/** Model to return list of JNDI bindings at given location */
public class JndiModel extends LoadableDetachableModel<List<Assoc<Object>>> {
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(JndiModel.class);
    
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
    public List<Assoc<Object>> load() {
        try {
            return jndiReader.read(location);
        } catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            throw new RuntimeException(se);
        }
    }

}
