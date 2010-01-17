package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.util.tester.WicketTester;

/** 
 * mock injector that also prepares application and tester,
 * and that lets application inject its components
 */
public class WicketMockInjector extends MockInjector {
    
    private final DemoApplication application;

    public WicketMockInjector(Class<?>... classes) {
        super(classes);
        application = get(DemoApplication.class);
        application.addComponentInstantiationListener(
                new GuiceComponentInjector(application, injector));
    }
    
    public DemoApplication application() {
        return application;
    }
    
    public WicketTester tester() {
        return new WicketTester(application());
    }
}
