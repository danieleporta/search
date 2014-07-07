package nl.xs4all.banaan.tst8.service.impl;


import static org.junit.Assert.assertTrue;

import java.util.List;

import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.junit.Before;
import org.junit.Test;

/**
 * Test that the JNDI reader works as expected.
 * @author konijn
 *
 */
public class JndiReaderImplTest {

    private MockInjector injector;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
    }
    
    @Test
    public void testRead() throws ServiceException {
        JndiReader jndiReader = injector.get(JndiReader.class);
        List<Assoc<Object>> list = jndiReader.read("elders");
        assertTrue(list.size() == 1);
    }
    
    @Test
    public void testRead2() throws ServiceException {
        JndiReader jndiReader = injector.get(JndiReader.class);
        List<Assoc<Object>> list = jndiReader.read("");
        assertTrue(list.size() == 5);
    }
    
    @Test
    public void testReadIsSorted() throws ServiceException {
        JndiReader jndiReader = injector.get(JndiReader.class);
        List<Assoc<Object>> list = jndiReader.read("");
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1).compareTo(list.get(i)) <= 0);
            assertTrue(list.get(i - 1).getKey().compareTo(list.get(i).getKey()) <= 0);
        }
    }
}
