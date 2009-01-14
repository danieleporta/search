package nl.xs4all.banaan.tst8.service;


/**
 * Return system properties in wicket-friendly format.
 * to be extended to find more props.
 * @author konijn
 *
 */
public interface PropertyReader {

    /**
     * Read properties given resource name.
     * @param path TODO
     * @return
     */
    public PropertyList read(String path) throws ServiceException;

}
