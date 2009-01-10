package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;


public class JndiPage extends BasePage {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(JndiPage.class);

    public JndiPage(final PageParameters parameters) {
        super (parameters);
        try {
            JndiList jndiList = new JndiList();
            add(new JndiPanel("jndi", jndiList.getList()));
        }
        catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            setResponsePage(ErrorPage.class);
        }
    }
}
