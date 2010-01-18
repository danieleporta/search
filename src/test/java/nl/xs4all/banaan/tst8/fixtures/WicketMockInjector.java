package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.util.tester.WicketTester;

/** 
 * mock injector that also prepares application and tester,
 * and that lets application inject its components
 */
public class WicketMockInjector extends MockInjector {

    public WicketMockInjector(Class<?>... classes) {
        super(classes);
    }
    
    public DemoApplication application() {
        return get(DemoApplication.class);
    }
    
    public WicketTester tester() {
        return get(WicketTester.class);
    }
}
