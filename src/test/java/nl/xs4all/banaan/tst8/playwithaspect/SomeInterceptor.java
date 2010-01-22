package nl.xs4all.banaan.tst8.playwithaspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;

/** some interceptor we want to test. */
@Ignore
public class SomeInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        invocation.proceed();
        return invocation.proceed();
    }
}
