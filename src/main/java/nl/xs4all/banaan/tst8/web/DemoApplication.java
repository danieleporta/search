package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPanel;
import nl.xs4all.banaan.tst8.web.letter.LetterPanel;
import nl.xs4all.banaan.tst8.web.menu.MenuList;
import nl.xs4all.banaan.tst8.web.menu.MenuPage;
import nl.xs4all.banaan.tst8.web.notificator.NotificationPanel;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePanel;
import nl.xs4all.banaan.tst8.web.param.ParamPanel;
import nl.xs4all.banaan.tst8.web.property.PropertyPanel;
import nl.xs4all.banaan.tst8.web.upload.UploadPanel;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.panel.Panel;
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
        addToMenu(JndiPanel.class, "jndi");
        addToMenu(LetterPanel.class, "letter");
        addToMenu(ParamPanel.class, "param");
        addToMenu(PropertyPanel.class, "property");
        addToMenu(NotificationPanel.class, "notificator");
        addToMenu(UploadPanel.class, "upload");
        addToMenu(OnchangePanel.class, "onchange");
        
        // try accessing these via a wicket tester
        mountBookmarkablePage("menu", MenuPage.class);
        mountBookmarkablePage("error", ErrorPage.class);        
        
        // following error page is only used in production mode
        IApplicationSettings settings = getApplicationSettings();
        settings.setInternalErrorPage(ErrorPage.class);
        
        // you can show it in development mode, but that breaks
        // junit testing of exceptions.
        // getExceptionSettings().setUnexpectedExceptionDisplay(
        //        IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

    }

    private void addToMenu(Class<? extends Panel> target, String name) {
        menuList.add(name, target);
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
