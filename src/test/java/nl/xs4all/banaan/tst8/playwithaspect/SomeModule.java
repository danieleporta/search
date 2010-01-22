package nl.xs4all.banaan.tst8.playwithaspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.easymock.EasyMock;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;

/** 
 * to test an interceptor, set up a module where the interface
 * is bound to a mock, and every invocation is intercepted.
 */
public class SomeModule<T> extends AbstractModule {
    
    private Class<T> iface;
    private MethodInterceptor interceptor;
    private T mock;

    public SomeModule(Class<T> iface, MethodInterceptor interceptor) {
        this.iface = iface;
        this.interceptor = interceptor;
    }

    @Override
    protected void configure() {
        bind(iface).toProvider(new Provider<T>() {

            public T get() {
                mock = EasyMock.createMock(iface);
                System.out.println("make mock " + mock);
                return mock;
            }
        });
        
        // this wont work: interceptor does not get added to objects
        // created by provider.
        bindInterceptor(
                Matchers.any(),
                Matchers.any(), 
                interceptor);
    }
    
    public T getMock() {
        System.out.println("get mock " + mock);
        return mock;
    }
}
