package nl.xs4all.banaan.tst8.playwithlogging;

import org.slf4j.Logger;
import org.slf4j.Marker;

import com.google.inject.Inject;

/** 
 * Logger for class T; this just delegates to SLF4J.
 */
public class MyLoggerImpl<T> implements Logger {
    
    private final Logger logger;
    
    @Inject
    public MyLoggerImpl(Logger logger) {
        this.logger = logger;
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    public void debug(Marker marker, String format, Object[] argArray) {
        logger.debug(marker, format, argArray);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
    }

    public void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    public void debug(String format, Object[] argArray) {
        logger.debug(format, argArray);
    }

    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    public void error(Marker marker, String format, Object[] argArray) {
        logger.error(marker, format, argArray);
    }

    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

    public void error(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    public void error(String format, Object arg) {
        logger.error(format, arg);
    }

    public void error(String format, Object[] argArray) {
        logger.error(format, argArray);
    }

    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    public void info(Marker marker, String format, Object[] argArray) {
        logger.info(marker, format, argArray);
    }

    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
    }

    public void info(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    public void info(String format, Object[] argArray) {
        logger.info(format, argArray);
    }

    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    public void trace(Marker marker, String format, Object[] argArray) {
        logger.trace(marker, format, argArray);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
    }

    public void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    public void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    public void trace(String format, Object[] argArray) {
        logger.trace(format, argArray);
    }

    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    public void trace(String msg) {
        logger.trace(msg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    public void warn(Marker marker, String format, Object[] argArray) {
        logger.warn(marker, format, argArray);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
    }

    public void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
    }

    public void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    public void warn(String format, Object[] argArray) {
        logger.warn(format, argArray);
    }

    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public String getName() {
        return logger.getName();
    }
}
