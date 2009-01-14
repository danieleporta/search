package nl.xs4all.banaan.tst8.web.base;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPanel;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * BasePage provides menu and feedback; other pages
 * will add their panels to this.
 * @author konijn
 *
 */
public abstract class BasePage extends WebPage {
    
    protected static Logger logger = Logger.getLogger(BasePage.class);

    public BasePage() {
        add(new MenuPanel("menu"));
        add(new FeedbackPanel("feedback"));
        getSession().info("Setting up basepage");
    }

    /**
     * Isolate the tricky bits in an init function that catches
     * exceptions.
     */
    protected void init() {
        try {
            doInit();
        }
        catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            setResponsePage(ErrorPage.class);
        }
    }

    public void doInit() throws ServiceException {
    }
}
