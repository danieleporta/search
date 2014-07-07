package nl.xs4all.banaan.tst8.service;

import java.util.List;

import nl.xs4all.banaan.tst8.util.Assoc;


/** Return properties in wicket-friendly format. */
public interface PropertyReader {

    /**
     * Read properties given resource name.
     * @param path resource to read; or system properties if null or empty.
     * @return
     */
    public List<Assoc<String>> read(String path) throws ServiceException;

}
