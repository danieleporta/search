package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.Notificator;
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
     * services injected via spring
     */
    private JndiReader jndiReader;
    private PropertyReader propertyReader;
    private Notificator notificator;
    
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
        
        // following error page is only used in production mode
        IApplicationSettings settings = getApplicationSettings();
        settings.setInternalErrorPage(ErrorPage.class);
        
        // you can show it in development mode, but that breaks
        // junit testing of exceptions.
        // getExceptionSettings().setUnexpectedExceptionDisplay(
        //        IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

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
    
    public void setNotificator(Notificator notificator) {
        this.notificator = notificator;
    }
    
    public Notificator getNotificator() {
        return notificator;
    }


    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<?> getHomePage() {
        return HomePage.class;
    }
}
