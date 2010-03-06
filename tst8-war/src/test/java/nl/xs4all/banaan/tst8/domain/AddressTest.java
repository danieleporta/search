package nl.xs4all.banaan.tst8.domain;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.ADDRESS1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.CITY1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.STREET1;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddressTest {
    
    @Test
    public void testAddress() {
        assertEquals(NAME1, ADDRESS1.getName());
        assertEquals(STREET1, ADDRESS1.getStreet());        
        assertEquals(CITY1, ADDRESS1.getCity());
    }

}
