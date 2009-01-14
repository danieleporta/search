package nl.xs4all.banaan.tst8.service;

import java.util.Properties;

/**
 * implement property reader
 * @author konijn
 *
 */
public class PropertyReaderImpl implements PropertyReader {
    public PropertyList read () {
        Properties props = System.getProperties();
        return new PropertyList(props);
    }
}
