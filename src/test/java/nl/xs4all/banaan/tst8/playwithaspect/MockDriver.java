package nl.xs4all.banaan.tst8.playwithaspect;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.easymock.EasyMock;
import org.junit.Ignore;

import com.google.inject.internal.cglib.proxy.Enhancer;
import com.google.inject.internal.cglib.proxy.MethodProxy;

/**
 * Driver to test a MethodInterceptor. It provides a mock that will be invoked
 * through the interceptors {@link MethodInvocation}, and a proxy that will forward
 * all calls to the interceptor.
 * 
 *  @param <T> the interface the interceptor should adhere to.
 */
@Ignore
public class MockDriver<T> {
    private final T mock;
    private final T proxy;
    
    /**
     * create {@link MockDriver}.
     * @param iface the interface the interceptor should adhere to
     * @param interceptor the interceptor under test
     */
    @SuppressWarnings("unchecked")
    public MockDriver(Class<T> iface, final SomeInterceptor interceptor) {
        this.mock = EasyMock.createMock(iface);
        
        Enhancer e = new Enhancer();
        e.setInterfaces(new Class[]{iface} );
        e.setCallback(new com.google.inject.internal.cglib.proxy.MethodInterceptor() {
            public Object intercept(Object object, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable 
            {
                return interceptor.invoke(
                        new MockInvocation<T>(mock, method, args, proxy));
            }
        });
        this.proxy = (T) e.create();
    }
    
    /** will receive all invocations the interceptor passes on */
    public T getMock() {
        return mock;
    }
    
    /** calls to this will be fed to the interceptor */
    public T getProxy() {
        return proxy;
    }
}
