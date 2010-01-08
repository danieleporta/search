package nl.xs4all.banaan.tst8.playwithlogging;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public class MyLoggerProvider<T> implements Provider<Logger> {
    
    private TypeLiteral<MyLogger<T>> t;

    @Inject
    public MyLoggerProvider(TypeLiteral<MyLogger<T>> t) {
        this.t = t;
    }

    public Logger get() {
        ParameterizedType type = (ParameterizedType) t.getType();
        Class<?> actualParameterClass = (Class<?>) type.getActualTypeArguments()[0];
        Logger logger = LoggerFactory.getLogger(actualParameterClass);
        return logger;
    }

}
