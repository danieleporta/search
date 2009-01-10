package nl.xs4all.banaan.tst8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericBindingTest {

    private GenericBinding<Integer> binding;

    @Before
    public void setUp() throws Exception {
	binding = new GenericBinding<Integer>("aap", 17);
    }

    @After
    public void tearDown() throws Exception {
	binding = null;
    }
    
    @Test
    public void testBinding () {
	assertEquals(binding.getKey(), "aap");
	assertEquals(binding.getValue(), new Integer(17));
    }
    

}
