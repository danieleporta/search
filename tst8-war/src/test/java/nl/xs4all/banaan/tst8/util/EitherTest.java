package nl.xs4all.banaan.tst8.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EitherTest {
    
    private Either<String, Object> good;
    private Either<Object,String> bad;

    @Before
    public void setUp() {
        good = Either.good("aap");
        bad = Either.bad("noot");
    }
    
    @Test
    public void testGood() {
        assertTrue(good.isGood());
        assertEquals("aap", good.getGood());
    }
    
    @Test
    public void testBad() {
        assertFalse(bad.isGood());
        assertEquals("noot", bad.getBad());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetGoodFromBad() {
        bad.getGood();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetBadFromGood() {
        good.getBad();
    }

}
