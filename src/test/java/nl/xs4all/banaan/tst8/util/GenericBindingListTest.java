package nl.xs4all.banaan.tst8.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericBindingListTest {
    private GenericBindingList<String> bl;

    @Before
    public void setUp() throws Exception {
	bl = new GenericBindingList<String>();
	bl.add("aap", "aapval");
	bl.add("noot", "nootval");
	bl.add("mies", "miesval");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetList() {
	List<GenericBinding<String>> l = bl.getList();
	assertEquals(l.get(0).getKey(), "aap");
	assertEquals(l.get(1).getKey(), "mies");
	assertEquals(l.get(2).getKey(), "noot");
    }
}
