package nl.xs4all.banaan.tst8.playwithlogging;

import java.lang.annotation.Annotation;

public class MyLoggingImpl implements MyLogging {
    // eclipse may complain about this.

    private final Class<?> value;
    
    MyLoggingImpl(Class<?> type) {
        value = type;
    }
    
    public Class<?> value() {
        return value;
    }

    public Class<? extends Annotation> annotationType() {
        return MyLogging.class;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyLoggingImpl)) {
            return false;
        }
        MyLoggingImpl other = (MyLoggingImpl) obj;
        return value.equals(other.value());
    }

    @Override
    public int hashCode() {
        // This is specified in java.lang.Annotation.
        return (127 * "value".hashCode()) ^ value.hashCode();
    }
}
