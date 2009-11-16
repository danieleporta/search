package nl.xs4all.banaan.tst8.wiring;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Factory used by web.xml to let Guice make an instance of the wicket
 * application.
 * 
 * We provide the injector to the application; this allows wicket to
 * inject its components after instantiating them.
 * 
 * @author konijn
 * 
 */
public abstract class BaseWebApplicationFactory implements IWebApplicationFactory {

    /** override this to inform Guice what components go in */
    public abstract Module getModule();

    public WebApplication createApplication(WicketFilter filter) {
        Injector injector = Guice.createInjector(
                new BaseApplicationModule(),
                getModule());
        WebApplication application = injector.getInstance(DemoApplication.class);
        application.addComponentInstantiationListener(
                new GuiceComponentInjector(application, injector));
        return application;
    }
}
