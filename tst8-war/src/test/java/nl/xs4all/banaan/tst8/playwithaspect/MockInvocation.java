package nl.xs4all.banaan.tst8.playwithaspect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;

import com.google.inject.internal.cglib.proxy.MethodProxy;

/** 
 * An invocation suitable to feed to a {@link MethodInterceptor};
 * when proceeding, it pass the call to a mock. 
 */
@Ignore
public class MockInvocation<T> implements MethodInvocation {

    private final T mock;
    private final Method method;
    private final Object[] args;
    private final MethodProxy proxy;

    public MockInvocation(T mock, Method method, Object[] args, MethodProxy proxy) {
        this.mock = mock;
        this.method = method;
        this.proxy = proxy;
        this.args = args;
    }
    
    public Object proceed() throws Throwable {
        return proxy.invoke(mock, args);
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return args;
    }

    public AccessibleObject getStaticPart() {
        return getMethod();
    }

    public Object getThis() {
        return proxy;
    }
}