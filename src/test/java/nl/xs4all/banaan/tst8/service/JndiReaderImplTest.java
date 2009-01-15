package nl.xs4all.banaan.tst8.service;


import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContext;

import static org.junit.Assert.*;

/**
 * Test that the JNDI reader works as expected.
 * @author konijn
 *
 */
public class JndiReaderImplTest {
    private JndiReaderImpl reader;

    @Before
    public void setUp() throws Exception {
        Context initialContext = new SimpleNamingContext();
        initialContext.bind("java:comp/env/elders/groet", "goedemorgen");
        reader = new JndiReaderImpl();
        reader.setInitialContext(initialContext);
    }

    @After
    public void tearDown() throws Exception {
        reader = null;
    }
    
    @Test
    public void testRead () throws ServiceException {
        JndiList list = reader.read("elders");
        assertTrue(list.getList().size() > 0);
    }

}
