package nl.xs4all.banaan.tst8.wiring;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import nl.xs4all.banaan.tst8.service.Services;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.CreationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * Test that guice injector as configured
 * with application module returns expected objects.
 * @author konijn
 *
 */
public class TestApplicationModuleTest {

    private Injector injector;

    @Before
    public void startUp() {
        injector = Guice.createInjector(
                new BaseApplicationModule(),
                new TestApplicationModule());
    }
   
    @Test
    public void testCreateOneInstance() {
        injector.getInstance(Services.class);
    }
    
    @Test
    public void testCreateOneInstanceAndTestBindings() {
        Services services = injector.getInstance(Services.class);
        assertNotNull(services.getBuildInfo());
    }
    
    @Test
    public void testBuildInfoProperlyWiredUp() {
        Services services = injector.getInstance(Services.class);
        assertNotNull(services.getBuildInfo());
        assertNotNull(services.getBuildInfo().getName());
    }
    
    @Test
    public void testNotifactorWiredUp() {
        Services services = injector.getInstance(Services.class);
        assertNotNull(services.getNotificator());
        // cant see inside
    }
    
    @Test
    public void testSingletonStaysUnchangesInsideInjector() {
        Services services = injector.getInstance(Services.class);
        Services services2 = injector.getInstance(Services.class);
        assertTrue(services == services2);
    }
    
    @Test
    public void testSingletonHasMeaningOnlyInASingleInjector() {
        Services services = injector.getInstance(Services.class);
        Injector injector2 = Guice.createInjector(
                new BaseApplicationModule(),
                new TestApplicationModule());
        Services services2 = injector2.getInstance(Services.class);
        assertTrue(services != services2);
    }
    
    @Test
    public void testApplicationIsWired() {
        DemoApplication application = injector.getInstance(DemoApplication.class);
        assertNotNull(application.getServices().getBuildInfo().getName());
    }
    
    /* Test whether bindings in module can override */
    
    private static class ModA extends AbstractModule {
        @Override protected void configure() {
            bind(String.class).toInstance("FromA");
        }
    }
    
    private static class ModB extends AbstractModule {
        @Override protected void configure() {
            bind(String.class).toInstance("FromB");
        }
    }

    @Test
    public void testThatBindingForStringIsFound() {
        Injector inj = Guice.createInjector(new ModA());
        String string = inj.getInstance(String.class);
        assertEquals("FromA", string);
    }
    
    @Test(expected=CreationException.class)
    public void testThatConflictingBindingsForStringAreDetected() {
        Guice.createInjector(new ModA(), new ModB());
    }
    
    @Test
    public void testThatBindingForStringCanBeOverridden() {
        Module sum = Modules.override(new ModA()).with(new ModB());
        Injector inj = Guice.createInjector(sum);
        String string = inj.getInstance(String.class);
        assertEquals("FromB", string);
    }
}
