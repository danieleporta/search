package nl.xs4all.banaan.tst8.domain;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS2;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.LETTER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.POSTAGE1;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LetterTest {
    
    @Test
    public void testLetter () {
        assertEquals(ADDRESS1, LETTER1.getFrom());
        assertEquals(ADDRESS2, LETTER1.getTo());        
        assertEquals(POSTAGE1, LETTER1.getPostage());
    }
}
