package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;

/**
 * Shows result of a JNDI lookup.
 * @author konijn
 *
 */
public class JndiPage extends BasePage {
    private static Logger logger = Logger.getLogger(JndiPage.class);

    /**
     * location relative to JNDI system root to display in this page.
     */
    private String location = "";

    public String getLocation() {
	return location;
    }
    
    public void setLocation(String location) {
	this.location = location;
    }
    
    public JndiPage() {
        try {
            add(new JndiPanel("jndi", location));
        }
        catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            setResponsePage(ErrorPage.class);
        }
    }
}
