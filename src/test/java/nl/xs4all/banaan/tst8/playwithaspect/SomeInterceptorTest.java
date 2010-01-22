package nl.xs4all.banaan.tst8.playwithaspect;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.internal.cglib.proxy.Callback;
import com.google.inject.internal.cglib.proxy.Enhancer;
import com.google.inject.internal.cglib.proxy.MethodProxy;

public class SomeInterceptorTest {

    // broken: cant invoke the interceptor simply by fiddling with injector
    @Ignore @Test
    public void shouldInvokeInterceptedMethod() {
        // the interceptor under test
        MethodInterceptor interceptor = new SomeInterceptor();
        
        // The injector that binds the interceptor 
        SomeModule<SomeInterface> module = new SomeModule(SomeInterface.class, interceptor);
        Injector injector = Guice.createInjector(module);
        
        // the mock with a method interceptor wrapped around it
        SomeInterface target = injector.getInstance(SomeInterface.class);

        // a mock for the interceptor to invoke
        SomeInterface mock = module.getMock();
        System.out.println("mock is " + mock);

        System.out.println(target);
        // we expect the interceptor to do the call twice
        String arg = "aap";
        EasyMock.expect(mock.run(arg,arg)).andReturn("aapaap").times(2);
        EasyMock.replay(mock);
        
        // invoke wrapped mock
        target.run(arg, arg);
        
        // verify aspect has invoked mock interface
        EasyMock.verify(mock);
        
    }

    @Test
    public void testCglib() {

        
        // this builds the proxy
        Enhancer e = new Enhancer();
        
        // proxy will respond to this interface
        e.setInterfaces(new Class[]{SomeInterface.class} );

        // this will be invoked by the proxy
        final SomeInterface mock = EasyMock.createMock(SomeInterface.class);
        EasyMock.expect(mock.run("aap", "noot")).andReturn("aapnoot");
        EasyMock.replay(mock);
        
        // this will be called when invoking the proxy
        Callback callback = new com.google.inject.internal.cglib.proxy.MethodInterceptor() {
            
            public Object intercept(Object arg0, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable {
                //System.out.println("koekoek!");
                return proxy.invoke(mock, args);
            }
        };
        e.setCallback(callback);
        
        SomeInterface object = (SomeInterface) e.create();

        // invoke the proxy, this should touch the mock
        object.run("aap", "noot");
        
        // verify that the mock was invoked
        EasyMock.verify(mock);
    }
 

    @Test
    public void testCglibWithInterceptor() {
        
        // the interceptor to be tested
        final SomeInterceptor interceptor = new SomeInterceptor();
        
        // this builds the proxy
        Enhancer e = new Enhancer();
        
        // proxy will respond to this interface
        e.setInterfaces(new Class[]{SomeInterface.class} );

        // this will be invoked by the proxy
        final SomeInterface mock = EasyMock.createMock(SomeInterface.class);
        EasyMock.expect(mock.run("aap", "noot")).andReturn("aapnoot").times(2);
        EasyMock.replay(mock);
        
        // this will be called when invoking the proxy
        Callback callback = new com.google.inject.internal.cglib.proxy.MethodInterceptor() {
            
            public Object intercept(Object arg0, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable {
                
                MyInvocation<SomeInterface> invocation = new MyInvocation(mock, method, args, proxy);
                // System.out.println("koekoek!");
                return interceptor.invoke(invocation);
            }
        };
        e.setCallback(callback);
        
        SomeInterface object = (SomeInterface) e.create();

        // invoke the proxy, this should touch the mock
        object.run("aap", "noot");
        
        // verify that the mock was invoked
        EasyMock.verify(mock);
    }

    /** an invocation the way Guice interceptors like them */
    public static class MyInvocation<T> implements MethodInvocation {
        private final T mock;
        private final Method method;
        private final Object[] args;
        private final MethodProxy proxy;

        public MyInvocation(T mock, Method method, Object[] args, MethodProxy proxy) {
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
}
