package nl.xs4all.banaan.tst8.fixtures;

import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.core.IsEqual.*;

/**
 * Given an interface to an object, it will return a proxy that logs all calls.
 * @author konijn
 *
 */
public class FlightRecorder {
    private List<FlightEvent> record;

    public FlightRecorder() {
        record = new LinkedList<FlightEvent>();
    }
    
    public Object enlist(Class<?> clazz, Object o) {
        Class<?>[] interfaces = new Class<?>[] { clazz };
        ClassLoader loader = clazz.getClassLoader();
        InvocationHandler handler = new FlightRecorderProbe(clazz, o, this);
        return Proxy.newProxyInstance(loader, interfaces, handler);
    }

    public void list() {
        list(System.out);
    }
    
    public void record(FlightEvent event) {
        this.record.add(event);
    }
    
    public void list(PrintStream out) {
        Integer i = 0;
        for (FlightEvent event : record) {
            out.println("===== Flight Record: " + i);
            out.println("Call: "+ event.getInterfaceName() 
                    + "." + event.getMethodName()
                    + "(" + event.getArguments() + ")");
            out.println("Result: " + event.getResult());
            out.println("Throws: " + event.getThrowable());
            i++;
        }
    }
    
    public List<FlightEvent> getRecord() {
        return record;
    }

    public void check(int index, 
            String interfaceName, String methodName,
            Object[] args, Object result, Throwable throwable)
    {
        assertTrue("index in bounds", 0 <= index && index < record.size());
        FlightEvent actual = record.get(index);
        assertEquals ("interface", interfaceName, actual.getInterfaceName());
        assertEquals ("method", methodName, actual.getMethodName());
        assertThat("arguments", actual.getArguments(), equalTo(args));
        assertEquals("result", result, actual.getResult());
        assertEquals("throws", throwable, actual.getThrowable());
    }
}
