package nl.xs4all.banaan.tst8.jndi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Show JNDI data.
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(JndiPanel.class);
    
    Context initialContext;

    public JndiPanel(String id) {
        super(id);
        getSession().info("building  jndi panel");
        String ic = "(none)";
        try {
            initialContext = new InitialContext();
            ic  = initialContext.toString();
        }
        catch (NamingException ne) {
            logger.error("Naming exception");
            getSession().error("naming exception");
        }
        add(new Label("initial", ic));

        // JNDI can query multiple namespaces;
        // the java:comp/env path gives access to bindings
        // that are provided by web.xml or the container.  
        // See servlet 2.4 spec, SRV 9.11, SRV 13.4.20, SRV 13.4.24, via
        // http://jcp.org/aboutJava/communityprocess/final/jsr154/index.html
        // 
        String path = "java:comp/env/";
        add(new Label("path", path));
        
        add(new PropertyListView("bindings", jndiBindingList(path)) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("name"));
                item.add(new Label("object"));
            }
        });

    }

    private List<Binding> jndiBindingList(String name) {
        List<Binding> result = new LinkedList<Binding>();
        try {
            NamingEnumeration<Binding> e = initialContext.listBindings(name);
            while (e.hasMoreElements()) {
                logger.info("adding element");
                result.add((Binding) e.nextElement());
            }            
        }
        catch (NamingException ne) {
            logger.error("Naming exception in listbindings");
            getSession().error("naming exception in listbindings");     
        }

        return Collections.unmodifiableList(result);
    }
}
