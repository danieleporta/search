package nl.xs4all.banaan.tst8.playwithlogging;

import java.lang.reflect.ParameterizedType;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;

/** 
 * this would implement a logger; we only show how it accesses
 * the name provided by injection.
 * 
 * Possibly it would be packaged as a provider that accesses a factory.
 * 
 * Passing the type literal is over engineering: if you are prepared
 * to have a binding per class that is logger, you can pass in classname
 * to here just as well.
 */
public class MyLoggerImpl<T> implements MyLogger<T> {
    
    private final String name;
    @Inject
    public MyLoggerImpl(TypeLiteral<MyLogger<T>> t) {
        ParameterizedType type = (ParameterizedType) t.getType();
        Class actualParameterClass = (Class) type.getActualTypeArguments()[0];
        this.name = actualParameterClass.getName();
    }
    
    public String getName() {
        return name;
    }

}
