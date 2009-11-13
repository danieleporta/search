package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ServiceModuleTest {

    private Injector injector;

    @Before
    public void startUp() {
        injector = Guice.createInjector(new ServiceModule());
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
        Injector injector2 = Guice.createInjector(new ServiceModule());
        Services services2 = injector2.getInstance(Services.class);
        assertTrue(services != services2);
    }
    
}
