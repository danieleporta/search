package nl.xs4all.banaan.tst8.fixtures;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FlightRecorderProbe implements InvocationHandler {
    private String interfaceName;
    private Object proxee;
    private FlightRecorder recorder;

    public FlightRecorderProbe(Class<?> clazz, Object proxee,
            FlightRecorder recorder) 
    {
        this.interfaceName = clazz.getSimpleName();
        this.proxee = proxee;
        this.recorder = recorder;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        try {
            Object result = method.invoke(proxee, args);
            recorder.record(new FlightEvent(
                    interfaceName, methodName, args, result, null));
            Class<?> resultClass = method.getReturnType();
            if (resultClass.isInterface()) {
                // suitable for recursive enlisting
                return recorder.enlist(resultClass, result);
            }

            return result;
        }
        catch (Throwable throwable) {
            recorder.record(new FlightEvent(
                    interfaceName, methodName, args, null, throwable));
            throw throwable;
        }
    }
}
