package nl.xs4all.banaan.tst8.playwithlogging;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import com.google.inject.BindingAnnotation;

/** indicate that a Logger should log for this class */
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
public @interface LogFor {
    /** want logging for this class */
    Class<?> value();
}
