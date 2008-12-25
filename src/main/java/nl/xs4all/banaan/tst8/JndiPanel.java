package nl.xs4all.banaan.tst8;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
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
        try {
            initialContext = new InitialContext();           
        }
        catch (NamingException ne) {
            logger.error("Naming exception");
        }
    }
}
