package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPage;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IApplicationSettings;

import com.google.inject.Inject;

/**
 * Application object for your web application. 
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class DemoApplication extends WebApplication {

    @Inject
    public DemoApplication() {
    }
    
    @Override
    protected void init() {
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

    
    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
