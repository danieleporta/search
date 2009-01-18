package nl.xs4all.banaan.tst8.fixtures;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.mock.jndi.SimpleNamingContext;

/**
 * Wrapper for SimpleNamingContext that takes map as constructor
 * argument rather than the (not-injectable) hashmap required
 * by parent type.
 * 
 * @author konijn
 *
 */
public class InitializedNamingContext extends SimpleNamingContext {
    public InitializedNamingContext (Map<String,Object> bound) {
        for (Entry<String, Object> e : bound.entrySet()) {
            bind(e.getKey(), e.getValue());
        }
    }
}
