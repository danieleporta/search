package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.wiring.TestApplicationModule;

import org.junit.Before;
import org.junit.Ignore;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Base class for tests that need injection.
 * @author konijn
 *
 */
@Ignore
public abstract class InjectedTest {
    private Injector injector;

    @Before
    public void startUp() {
        injector = Guice.createInjector(new TestApplicationModule());
    }
    
    public Services services() {
        return injector.getInstance(Services.class);
    }
    
    public DemoApplication application() {
        return injector.getInstance(DemoApplication.class);
    }
}
