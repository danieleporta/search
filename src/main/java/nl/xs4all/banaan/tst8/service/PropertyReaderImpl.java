package nl.xs4all.banaan.tst8.service;

import java.util.Enumeration;
import java.util.Properties;

/**
 * implement property reader
 * @author konijn
 *
 */
public class PropertyReaderImpl implements PropertyReader {
    public PropertyList read () {
        Properties props = System.getProperties();
        PropertyList result = new PropertyList();
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            result.add (key, value);
        }
        return result;
    }
}
