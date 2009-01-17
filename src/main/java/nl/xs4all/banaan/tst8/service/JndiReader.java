package nl.xs4all.banaan.tst8.service;

/**
 * Read list of objects from JNDI, set either in web.xml in war file,
 * or in configuration of the web server.
 * @author konijn
 *
 */
public interface JndiReader {

    /**
     * read objects from JNDI
     * @param location relative to 'java:comp/env' Context.
     * @return
     * @throws ServiceException
     */
    public abstract JndiList read(String location) throws ServiceException;

}
