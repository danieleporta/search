package nl.xs4all.banaan.tst8.service.impl;


import static org.junit.Assert.assertTrue;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;

import org.junit.Test;

/**
 * Test that the JNDI reader works as expected.
 * @author konijn
 *
 */
public class JndiReaderImplTest {

    @Test
    public void testRead () throws ServiceException {
        MockInjector injector = new MockInjector();
        JndiReader jndiReader = injector.get(JndiReader.class);
        JndiList list = jndiReader.read("elders");
        assertTrue(list.getList().size() > 0);
    }
}
