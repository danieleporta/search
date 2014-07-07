package nl.xs4all.banaan.tst8.playwithaspect;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

/** module that injects a combination of two interceptors */
public class CombinedModule extends AbstractModule {
    
    private final StringExpecter expecter;

    public CombinedModule(StringExpecter expecter) {
        this.expecter = expecter;
    }

    @Override
    protected void configure() {
        bind(SomeInterface.class).to(SomeInterfaceImpl.class).in(Singleton.class);    
        bind(StringExpecter.class).toInstance(expecter);
        
        PrintInterceptor inner = new PrintInterceptor("inner");
        requestInjection(inner);
        PrintInterceptor outer = new PrintInterceptor("outer");
        requestInjection(outer);
        CombinedInterceptor interceptor = new CombinedInterceptor(inner, outer);
        
        bindInterceptor(
                Matchers.subclassesOf(SomeInterfaceImpl.class),
                Matchers.any(), 
                interceptor);
    }
}
