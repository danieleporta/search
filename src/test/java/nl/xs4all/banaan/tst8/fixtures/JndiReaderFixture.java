package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;


/**
 * Fixture for the reading of JNDI.
 * @author konijn
 *
 */
public class JndiReaderFixture implements JndiReader {
    
    public JndiList read (String location) throws ServiceException {
        JndiList result = new JndiList();

        if (location.equals("")) {
            result.add("aap", "aapval");
            result.add("noot", "nootval");
        }
        else if (location.equals("jdbc")) {
            result.add("mies", "miesval");
            result.add("wim", "wimval");
        }
        else {
            throw new ServiceException ("jndi fixture unknown argument",
        	    new RuntimeException("fake cause"));            
        }
        return result;
    }
}
