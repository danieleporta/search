package nl.xs4all.banaan.tst8.fixtures;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Use AOP to record method invocations.
 * @author konijn
 *
 */
public class FlightRecorderProbe {
    private FlightRecorder flightRecorder;

    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        String interfaceName = invocation.getSignature().getDeclaringTypeName();
        String methodName = invocation.getSignature().getName();
        Object [] args = invocation.getArgs();
        try {
            Object result = invocation.proceed();
            flightRecorder.record(new FlightEvent(
                    interfaceName, methodName, args, result, null));
            return result;
        }
        catch (Throwable throwable) {
            flightRecorder.record(new FlightEvent(
                    interfaceName, methodName, args, null, throwable));
            throw throwable;
        }
    }
    
    public void setFlightRecorder(FlightRecorder flightRecorder) {
        this.flightRecorder = flightRecorder;
    }
}
