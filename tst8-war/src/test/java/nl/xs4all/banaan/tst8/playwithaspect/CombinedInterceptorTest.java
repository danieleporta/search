package nl.xs4all.banaan.tst8.playwithaspect;


import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CombinedInterceptorTest {

    @Test
    public void testInterceptorProceeds() {
        StringExpecter expecter = new StringExpecter(
                "Before outer", 
                "Before inner", 
                "After inner",
                "After outer");
        SomeInterface mock = EasyMock.createMock(SomeInterface.class);
        PrintInterceptor inner = new PrintInterceptor("inner");
        inner.expecter = expecter;
        PrintInterceptor outer = new PrintInterceptor("outer");
        outer.expecter = expecter;
        CombinedInterceptor interceptor = new CombinedInterceptor(inner, outer);
        
        SomeInterface proxy = MockDriver.getProxy(SomeInterface.class, mock, interceptor);
        
        // expected behavior
        EasyMock.expect(mock.run("aap", "noot")).andReturn("aapnoot");
        EasyMock.replay(mock);

        // invoke the proxy, this should trigger the interceptor
        proxy.run("aap", "noot");
        
        // verify the expected behavior
        EasyMock.verify(mock);
    }
    
    @Test
    public void testInterceptorIsInjected() {
        StringExpecter expecter = new StringExpecter(
                "Before outer", 
                "Before inner", 
                "After inner",
                "After outer");        
        Injector injector = Guice.createInjector(new CombinedModule(expecter));
        SomeInterface instance = injector.getInstance(SomeInterface.class);
        assertEquals("aapnoot", instance.run("aap", "noot"));
    }

    @Test
    public void stringExpecterTest() {
        StringExpecter expecter = new StringExpecter("aap", "noot", "mies");
        expecter.pass("aap");
        expecter.pass("noot");
        expecter.pass("mies");
        expecter.done();
    }
}
