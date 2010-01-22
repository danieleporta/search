package nl.xs4all.banaan.tst8.playwithaspect;


import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Example of how to test an interceptor. Since Guice only applies
 * MethodInterceptors to objects created by the injector, and since the
 * interceptor itself is not injected, testing the interceptor cannot be done by
 * simply putting a few mocks in the injector.
 */
public class SomeInterceptorTest {

    @Test
    public void testInterceptorProceedsTwice() {
        // the interceptor to be tested
        SomeInterceptor interceptor = new SomeInterceptor();
        MockDriver<SomeInterface> driver = new MockDriver<SomeInterface>(SomeInterface.class, interceptor);
        
        // expected behavior
        final SomeInterface mock = driver.getMock();
        EasyMock.expect(mock.run("aap", "noot")).andReturn("aapnoot");
        EasyMock.expect(mock.run("noot", "aap")).andReturn("nootaap");
        EasyMock.replay(mock);

        // invoke the proxy, this should trigger the interceptor
        SomeInterface object = driver.getProxy();
        object.run("aap", "noot");
        
        // verify the expected behavior
        EasyMock.verify(mock);
    }
    
    @Test
    public void testInterceptorIsInjected() {
        Injector injector = Guice.createInjector(new SomeModule());
        SomeInterface instance = injector.getInstance(SomeInterface.class);
        assertEquals("nootaap", instance.run("aap", "noot"));
    }
}
