package nl.xs4all.banaan.tst8.service;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * Read list of objects from JNDI, set either in web.xml in war file,
 * or in configuration of the web server.
 * @author konijn
 *
 */
public class JndiReader {
    private static Logger logger = Logger.getLogger(JndiReader.class);
    
    public JndiList read (String location) throws ServiceException {
        // JNDI can query multiple namespaces;
        // the java:comp/env path gives access to bindings
        // that are provided by web.xml or the container.
        // See servlet 2.4 spec, SRV 9.11, SRV 13.4.20, SRV 13.4.24, via
        // http://jcp.org/aboutJava/communityprocess/final/jsr154/index.html
        //
        String base = "java:comp/env/";
        String path = base + location;
        JndiList result = new JndiList();
        
	try {
            logger.info("start jndilist init for " + path + "." );

            Context initialContext = new InitialContext();
            NamingEnumeration<Binding> e = initialContext.listBindings(path);
            logger.info("jndilist have context");
            while (e.hasMoreElements()) {
                Binding b = e.nextElement();
                result.add(b.getName(), b.getObject());
            }
            logger.info("jndilist completed iteration");
            
        } catch (NamingException ne) {
            logger.info("jndilist got an exception");
            throw new ServiceException ("JNDI failure", ne);
        }
        return result;
    }
}
