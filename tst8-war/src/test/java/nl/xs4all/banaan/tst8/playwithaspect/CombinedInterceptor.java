package nl.xs4all.banaan.tst8.playwithaspect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/** interceptor that invokes two nested interceptors */
public class CombinedInterceptor implements MethodInterceptor {

    private final MethodInterceptor inner;
    private final MethodInterceptor outer;

    public CombinedInterceptor(MethodInterceptor inner, MethodInterceptor outer) {
        this.inner = inner;
        this.outer = outer;
    }
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return outer.invoke(wrap (inner, invocation));
    }

    private MethodInvocation wrap(
            final MethodInterceptor inner,
            final MethodInvocation invocation) {
        return new MethodInvocation() {
            
            public Object proceed() throws Throwable {
                return inner.invoke(invocation);
            }
            
            public Object getThis() {
                return invocation.getThis();
            }
            
            public AccessibleObject getStaticPart() {
                return invocation.getStaticPart();
            }
            
            public Object[] getArguments() {
                return invocation.getArguments();
            }
            
            public Method getMethod() {
                return invocation.getMethod();
            }
        };
    }

}
