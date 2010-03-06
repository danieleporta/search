package nl.xs4all.banaan.tst8.playwithaspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;

/**
 * Some interceptor we want to test. 
 * It invokes underlying method twice, last time with arguments reversed.
 */
@Ignore
public class SomeInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        invocation.proceed();

        Object[] arguments = invocation.getArguments();
        Object last = arguments[1];
        arguments[1] = arguments[0];
        arguments[0] = last;
        return invocation.proceed();
    }
}
