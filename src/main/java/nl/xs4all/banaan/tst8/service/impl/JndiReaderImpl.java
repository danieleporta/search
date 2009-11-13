package nl.xs4all.banaan.tst8.service.impl;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

/**
 * Implement the reading of JNDI.
 * @author konijn
 *
 */
public class JndiReaderImpl implements JndiReader {
    private static Logger logger = Logger.getLogger(JndiReaderImpl.class);

    // This context is expected to give access to resource root container,
    // the 'java:comp/env' part should be filled in by the 
    // injecting Spring configuration.
    //
    // JNDI can query multiple namespaces;
    // the java:comp/env path gives access to resource bindings
    // that are provided by web.xml or the container.
    // See servlet 2.4 spec, SRV 9.11, SRV 13.4.20, SRV 13.4.24, via
    // http://jcp.org/aboutJava/communityprocess/final/jsr154/index.html
    //
    private Context initialContext;
    
    @Inject
    public void setInitialContext(Context initialContext) {
        this.initialContext = initialContext;
    }

    /* (non-Javadoc)
     * @see nl.xs4all.banaan.tst8.service.JndiReader#read(java.lang.String)
     */
    public JndiList read (String location) throws ServiceException {

        JndiList result = new JndiList();
        
        try {
            logger.debug("start jndilist init for " + location + "." );
            
            NamingEnumeration<Binding> e = initialContext.listBindings(location);
            logger.debug("jndilist have context");
            while (e.hasMoreElements()) {
                Binding b = e.nextElement();
                result.add(b.getName(), b.getObject());
            }
            logger.debug("jndilist completed iteration");
            
        } catch (NamingException ne) {
            logger.debug("jndilist got an exception");
            throw new ServiceException("JNDI location not found" + location, 
                    ne);
        }
        return result;
    }
}
