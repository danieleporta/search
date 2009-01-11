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
            result.add("dir1", "DIR1-NODE");
            result.add("jdbc", "JDBC-NODE");            
            result.add("noot", "nootval");

        }
        else if (location.equals("jdbc")) {
            result.add("mies", "miesval");
            result.add("wim", "wimval");
        }
        else if (location.equals("dir1")) {
            result.add("dir2", "DIR2-NODE");            
        }
        else if (location.equals("dir1/dir2")) {
            result.add("entry3", "entry3val");            
        }        
        else {
            throw new ServiceException ("jndi fixture unknown argument");            
        }
        return result;
    }
}
