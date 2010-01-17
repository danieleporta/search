package nl.xs4all.banaan.tst8.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import nl.xs4all.banaan.tst8.logging.LogFor;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.slf4j.Logger;

import com.google.inject.Inject;

/**
 * Implement the reading of JNDI.
 * @author konijn
 *
 */
public class JndiReaderImpl implements JndiReader {

    // This context is expected to give access to resource root container,
    // the 'java:comp/env' part should be filled in by the 
    // injecting configuration.
    //
    // JNDI can query multiple namespaces;
    // the java:comp/env path gives access to resource bindings
    // that are provided by web.xml or the container.
    // See servlet 2.4 spec, SRV 9.11, SRV 13.4.20, SRV 13.4.24, via
    // http://jcp.org/aboutJava/communityprocess/final/jsr154/index.html
    //
    private final Context initialContext;
    private final Logger logger;
    
    @Inject
    public JndiReaderImpl(Context initialContext,
            @LogFor(JndiReaderImpl.class) Logger logger) {
        this.initialContext = initialContext;
        this.logger = logger;
    }

    public List<Assoc<Object>> read (String location) throws ServiceException {
        List<Assoc<Object>> result = new ArrayList<Assoc<Object>>();
        
        try {
            logger.debug("start jndilist init for " + location + "." );
            
            NamingEnumeration<Binding> e = initialContext.listBindings(location);
            logger.debug("jndilist have context");
            while (e.hasMoreElements()) {
                Binding b = e.nextElement();
                result.add(new Assoc<Object>(b.getName(), b.getObject()));
            }
            logger.debug("jndilist completed iteration");
            
        } catch (NamingException ne) {
            logger.debug("jndilist got an exception");
            throw new ServiceException("JNDI location not found" + location, 
                    ne);
        }
        Collections.sort(result);
        return result;
    }
}
