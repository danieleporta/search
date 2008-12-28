package nl.xs4all.banaan.tst8.jndi;

import nl.xs4all.banaan.tst8.JndiList;
import nl.xs4all.banaan.tst8.ServiceException;
import nl.xs4all.banaan.tst8.base.BasePage;
import nl.xs4all.banaan.tst8.error.ErrorPage;

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
