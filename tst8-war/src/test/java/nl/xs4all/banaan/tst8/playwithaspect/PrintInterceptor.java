package nl.xs4all.banaan.tst8.playwithaspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

/** interceptor that logs something as quick proof of invocation */
public class PrintInterceptor implements MethodInterceptor {

    @Inject public StringExpecter expecter;
    
    private final String name;
    public PrintInterceptor(String name) {
        this.name = name;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        expecter.pass("Before " + name);
        Object result = invocation.proceed();
        expecter.pass("After " + name);
        return result;
    }
}
