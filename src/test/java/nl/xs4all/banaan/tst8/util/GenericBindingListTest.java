package nl.xs4all.banaan.tst8.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericBindingListTest {
    private AssocList<String> bl;

    @Before
    public void setUp() throws Exception {
        bl = new AssocList<String>();
        bl.add("aap", "aapval");
        bl.add("noot", "nootval");
        bl.add("mies", "miesval");
        bl.add("mies-postfix", "miesValPost");
        bl.add("zprefix-mies-postfix", "zval");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetList() {
        List<Assoc<String>> l = bl.getList();
        assertEquals("aap", l.get(0).getKey());
        assertEquals("mies", l.get(1).getKey());
        assertEquals("noot", l.get(3).getKey());
    }

    @Test
    public void testFilterListFound() {
        List<Assoc<String>> list = bl.filter("mies").getList();
        assertEquals(3, list.size());
    }

    @Test
    public void testFilterListNotFound() {
        List<Assoc<String>> list = bl.filter("schapen").getList();
        assertEquals(0, list.size());
    }    
}
