package nl.xs4all.banaan.tst8.fixtures;

/**
 * A flight recorder will record flight events.
 * @author konijn
 *
 */
public class FlightEvent {
    private String interfaceName;
    private String methodName;
    private Object[] arguments;
    private Object result;
    private Throwable throwable;
    
    public FlightEvent(
            String interfaceName, 
            String methodName, 
            Object[] arguments, 
            Object result, 
            Throwable throwable) 
    {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.result = result;
        this.throwable = throwable;
    };

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getResult() {
        return result;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
