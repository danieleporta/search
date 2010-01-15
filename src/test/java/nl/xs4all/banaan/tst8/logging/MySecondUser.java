package nl.xs4all.banaan.tst8.logging;

import org.slf4j.Logger;

/** some random interface to test mock, logging and injection. */
public interface MySecondUser {
    public abstract String getLoggerName();
    public Logger getLogger();
    public void log(String message);
}
