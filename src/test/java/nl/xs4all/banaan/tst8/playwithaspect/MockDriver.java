package nl.xs4all.banaan.tst8.playwithaspect;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;

import com.google.inject.internal.cglib.proxy.Enhancer;
import com.google.inject.internal.cglib.proxy.MethodProxy;

/**
 * Driver to test a MethodInterceptor. It provides a mock that will be invoked
 * through the interceptors {@link MethodInvocation}, and a proxy that will forward
 * all calls to the interceptor.
 */
@Ignore
public class MockDriver {
    
    /**
     * Create proxy that will adhere to iface, and forward everything to
     * interceptor with an invocation that refers to service. 
     * @param iface the interface the interceptor should adhere to
     * @param service that will receive anything forwarded by interceptor
     * @param interceptor the interceptor under test
     * @param <T> the interface the interceptor should adhere to
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> iface, final T service, final SomeInterceptor interceptor) {
        Enhancer e = new Enhancer();
        e.setInterfaces(new Class[]{iface} );
        e.setCallback(new com.google.inject.internal.cglib.proxy.MethodInterceptor() {
            public Object intercept(Object object, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable 
            {
                return interceptor.invoke(
                        new MockInvocation<T>(service, method, args, proxy));
            }
        });
        return (T) e.create();
    }
}
