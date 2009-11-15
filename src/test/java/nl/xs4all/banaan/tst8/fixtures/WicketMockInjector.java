package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.web.DemoApplication;

/** mock injector that also prepares application and tester */
public class WicketMockInjector extends MockInjector {
    private final  BasePageTester tester;
    
    public WicketMockInjector(Class<?>... classes) {
        super(classes);
        tester = new BasePageTester(application());
    }
    
    public DemoApplication application() {
        return get(DemoApplication.class);
    }
    
    public BasePageTester tester() {
        return tester;
    }
}
