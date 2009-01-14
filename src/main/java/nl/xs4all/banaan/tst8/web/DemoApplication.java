package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.menu.MenuList;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IApplicationSettings;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.settings.Settings;

/**
 * Application object for your web application. 
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class DemoApplication extends WebApplication
{   
    private MenuList menuList;
    
    /**
     * jndi reader injected via spring
     */
    private JndiReader jndiReader;
    private PropertyReader propertyReader;
    
    public static DemoApplication get() {
        return (DemoApplication) Application.get();
    }
    
    @Override
    protected void init() {
        menuList = new MenuList();
        menuList.add("home", HomePage.class);
        menuList.add("jndi", JndiPage.class);
        menuList.add("param", ParamPage.class);        
        menuList.add("property", PropertyPage.class);
        
        IApplicationSettings settings = getApplicationSettings();
        settings.setInternalErrorPage(ErrorPage.class);
        
        // show internal error page rather than default developer page
        getExceptionSettings().setUnexpectedExceptionDisplay(
                IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

    }
	
    public MenuList getMenuList() {
        return menuList;
    }
    

    public void setJndiReader(JndiReader jndiReader) {
        this.jndiReader = jndiReader;
    }

    public JndiReader getJndiReader() {
        return jndiReader;
    }

    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }

    public PropertyReader getPropertyReader() {
        return propertyReader;
    }


    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<?> getHomePage() {
        return HomePage.class;
    }
}
