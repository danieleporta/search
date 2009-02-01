package nl.xs4all.banaan.tst8.fixtures;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Use AOP to record method invocations.
 * @author konijn
 *
 */
public class FlightRecorder {
    private List<FlightEvent> record;

    public FlightRecorder() {
        record = new LinkedList<FlightEvent>();
    }
    
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        String interfaceName = invocation.getSignature().getDeclaringTypeName();
        String methodName = invocation.getSignature().getName();
        Object [] args = invocation.getArgs();
        try {
            Object result = invocation.proceed();
            record.add(new FlightEvent(
                    interfaceName, methodName, args, result, null));
            return result;
        }
        catch (Throwable throwable) {
            record.add(new FlightEvent(
                    interfaceName, methodName, args, null, throwable));
            throw throwable;
        }
    }

    public void list() {
        list(System.out);
    }
    
    public void list(PrintStream out) {
        out.println("===== Flight Record Starts =====");
        Integer i = 0;
        for (FlightEvent event : record) {
            out.println("===== Flight Record: " + i);
            out.println("Call("
                    + event.getArguments().length
                    + "): "+ event.getInterfaceName() 
                    + "." + event.getMethodName()
                    + "(" + event.getArguments() + ")");
            out.println("Result: " + event.getResult());
            out.println("Throws: " + event.getThrowable());
            i++;
        }
        out.println("===== Flight Record Ends =====");
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
