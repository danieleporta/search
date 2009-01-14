package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;

/**
 * Shows result of a JNDI lookup.
 * @author konijn
 *
 */
public class JndiPage extends BasePage {
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
    
    public JndiPage(PageParameters pageParameters) {
        setLocation(pageParameters.getString("location", ""));
        init();        
    }
    
    public JndiPage() {
        setLocation("");
        init();
    }
    
    public JndiPage(String location) {
        setLocation(location);
        init();
    }

    @Override
    public void doInit() throws ServiceException {
        add(new JndiPanel("jndi", location));
    }
}
