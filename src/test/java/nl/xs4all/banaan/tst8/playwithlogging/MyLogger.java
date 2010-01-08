package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;
import org.slf4j.Logger;

/** 
 * the idea is to construct something that knows about its type parameter.
 * This should make it possible to inject loggers.
 */
@Ignore
public interface MyLogger<T> extends Logger {

}
