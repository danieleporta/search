package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.letter.LetterPage;
import nl.xs4all.banaan.tst8.web.menu.MenuList;
import nl.xs4all.banaan.tst8.web.notificator.NotificatorPage;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePage;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;
import nl.xs4all.banaan.tst8.web.upload.UploadPage;

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
    private Services services;
    
    public static DemoApplication get() {
        return (DemoApplication) Application.get();
    }
    
    @Override
    protected void init() {
        menuList = new MenuList();
        menuList.add("home", HomePage.class);
        menuList.add("jndi", JndiPage.class);
        menuList.add("letter", LetterPage.class);
        menuList.add("param", ParamPage.class);        
        menuList.add("property", PropertyPage.class);
        menuList.add("notificator", NotificatorPage.class);
        menuList.add("upload", UploadPage.class);
        menuList.add("onchange", OnchangePage.class);
        
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
    
    public void setServices(Services services) {
        this.services = services;
    }
    
    public Services getServices() {
        return services;
    }

    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<?> getHomePage() {
        return HomePage.class;
    }
}
