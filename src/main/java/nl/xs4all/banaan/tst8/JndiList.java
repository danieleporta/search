package nl.xs4all.banaan.tst8;

import java.util.LinkedList;
import java.util.List;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class JndiList {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(JndiList.class);

    private List<GenericBinding<Object>> list;

    public JndiList() throws ServiceException {
        list = new LinkedList<GenericBinding<Object>>();
        init();
    }

    public List<GenericBinding<Object>> getList() {
        return list;
    }

    private void add(String key, Object value) {
        logger.debug("Adding " + key);
        list.add(new GenericBinding<Object>(key, value));
    }

    private void init() throws ServiceException {
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
