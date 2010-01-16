package nl.xs4all.banaan.tst8.service;


/** Return properties in wicket-friendly format. */
public interface PropertyReader {

    /**
     * Read properties given resource name.
     * @param path resource to read; or system properties if null or empty.
     * @return
     */
    public PropertyList read(String path) throws ServiceException;

}
