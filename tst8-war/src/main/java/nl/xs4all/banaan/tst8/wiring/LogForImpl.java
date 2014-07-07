package nl.xs4all.banaan.tst8.wiring;

import java.lang.annotation.Annotation;

import nl.xs4all.banaan.tst8.logging.LogFor;

/**
 * implement the LogFor annotation, so that we can create instances that can
 * be equality tested against annotations on found near at-inject. 
 */
public class LogForImpl implements LogFor {
    // eclipse may complain about this, because implementing annotations is uncommon

    // single argument annotations should use this field name,
    // so that argument name may be ommited when using the annotation
    private final Class<?> value;
    
    /**
     *  An implementation of the LogFor annotation, suitable to be compared
     *  for equality against an annotation lifted with reflection from some
     *  constructor that is about to be injected.
     */
    public LogForImpl(Class<?> type) {
        value = type;
    }
    
    public Class<?> value() {
        return value;
    }

    public Class<? extends Annotation> annotationType() {
        return LogFor.class;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LogForImpl)) {
            return false;
        }
        LogForImpl other = (LogForImpl) obj;
        return value.equals(other.value());
    }

    @Override
    public int hashCode() {
        // This is specified in java.lang.Annotation.
        return (127 * "value".hashCode()) ^ value.hashCode();
    }
}
