package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;

/** 
 * the idea is to construct something that knows about its type parameter.
 * This should make it possible to inject loggers.
 * 
 * In a real implementation, this would extend slf4j Logger interface.
 */
@Ignore
public interface MyLogger<T> {
    /** the name as derived from type literal */
    public String getName();
}
