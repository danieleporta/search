package nl.xs4all.banaan.tst8.playwithlogging;

import org.slf4j.Logger;

/** some random interface to test mock, logging and injection. */
public interface MySecondUser {
    public abstract String getLoggerName();
    public Logger getLogger();
    public void log(String message);
}
