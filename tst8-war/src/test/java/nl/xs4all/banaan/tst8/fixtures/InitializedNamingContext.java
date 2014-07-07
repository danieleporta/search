package nl.xs4all.banaan.tst8.fixtures;

import org.springframework.mock.jndi.SimpleNamingContext;

/**
 * Wrapper for SimpleNamingContext for testing purposes.
 * 
 * @author konijn
 *
 */
public class InitializedNamingContext extends SimpleNamingContext {
    public InitializedNamingContext() {
        bind("elders/groet", "goedemorgen");
        bind("aap", "aapval");
        bind("dir1", "DIR1-NODE");
        bind("jdbc", "JDBC-NODE");
        bind("noot", "nootval");
        bind("jdbc/mies", "miesval");
        bind("jdbc/wim", "wimval");
        bind("dir1/dir2", "DIR2-NODE");
        bind("dir1/dir2/entry3", "entry3val");
    }
}
