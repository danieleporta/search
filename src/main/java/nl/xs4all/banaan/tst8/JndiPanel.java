package nl.xs4all.banaan.tst8;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
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
        add (new Label("initial", ic));
        
        
    }
}
