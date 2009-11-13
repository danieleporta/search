package nl.xs4all.banaan.tst8.service;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.junit.Ignore;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Sketch of the wicket applicaton factory, that will
 * create the application.  Inspired by SpringWicketApplication.
 * 
 * We could provide the injector to the application;
 * this would allow wicket to inject its components after instantiating them.
 * @author konijn
 *
 */
@Ignore
public class TestWebApplicationFactory implements IWebApplicationFactory {
    public WebApplication createApplication(WicketFilter filter) {
        Injector injector = Guice.createInjector(new TestApplicationModule());
        WebApplication application = injector.getInstance(DemoApplication.class);
        return application;
    }
}
