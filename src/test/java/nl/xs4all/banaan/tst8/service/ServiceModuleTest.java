package nl.xs4all.banaan.tst8.service;


import nl.xs4all.banaan.tst8.service.impl.ServicesImpl;

import org.junit.Test;
import org.springframework.util.Assert;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ServiceModuleTest {

    @Test
    public void testCreatingOneInjector() {
        Guice.createInjector(new ServiceModule());
    }
    
    @Test
    public void testCreateOneInstance() {
        Injector injector = Guice.createInjector(new ServiceModule());
        injector.getInstance(ServicesImpl.class);
    }
    
    @Test
    public void testCreateOneInstanceAndTestBindings() {
        Injector injector = Guice.createInjector(new ServiceModule());
        Services services = injector.getInstance(ServicesImpl.class);
        Assert.notNull(services.getBuildInfo());
    }
    
    @Test
    public void testBuildInfoProperlyWiredUp() {
        Injector injector = Guice.createInjector(new ServiceModule());
        Services services = injector.getInstance(ServicesImpl.class);
        Assert.notNull(services.getBuildInfo());
        Assert.notNull(services.getBuildInfo().getName());
    }
    
    @Test
    public void testNotifactorWiredUp() {
        Injector injector = Guice.createInjector(new ServiceModule());
        Services services = injector.getInstance(ServicesImpl.class);
        Assert.notNull(services.getNotificator());
        // cant see inside
    }
}
