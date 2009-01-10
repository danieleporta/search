package nl.xs4all.banaan.tst8.service;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import nl.xs4all.banaan.tst8.util.GenericBindingList;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;

import org.apache.log4j.Logger;


public class JndiList extends GenericBindingList<Object> {
    private static Logger logger = Logger.getLogger(JndiPage.class);
    
    public JndiList() throws ServiceException {
        super();
        
        // JNDI can query multiple namespaces;
        // the java:comp/env path gives access to bindings
        // that are provided by web.xml or the container.
        // See servlet 2.4 spec, SRV 9.11, SRV 13.4.20, SRV 13.4.24, via
        // http://jcp.org/aboutJava/communityprocess/final/jsr154/index.html
        //
        String path = "java:comp/env/";
        try {
            logger.info("start jndilist init");

            Context initialContext = new InitialContext();
            NamingEnumeration<Binding> e = initialContext.listBindings(path);
            logger.info("jndilist have context");
            while (e.hasMoreElements()) {
                Binding b = e.nextElement();
                add(b.getName(), b.getObject());
            }
            logger.info("jndilist completed iteration");
            
        } catch (NamingException ne) {
            logger.info("jndilist got an exception");
            throw new ServiceException ("JNDI failure", ne);
        }
    }
}
