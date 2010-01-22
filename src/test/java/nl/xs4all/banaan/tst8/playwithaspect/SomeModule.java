package nl.xs4all.banaan.tst8.playwithaspect;

import org.junit.Ignore;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

/** 
 * Module that applies an interceptor to all invocations
 * of SomeInterfaceImpl objects.  The point is how to test the interceptor;
 * see {@link SomeInterceptorTest}. 
 */
@Ignore
public class SomeModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(SomeInterface.class).to(SomeInterfaceImpl.class).in(Singleton.class);        
        bindInterceptor(
                Matchers.subclassesOf(SomeInterfaceImpl.class),
                Matchers.any(), 
                new SomeInterceptor());
    }
}
