package nl.xs4all.banaan.tst8.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;

/**
 * implement property reader
 * @author konijn
 *
 */
public class PropertyReaderImpl implements PropertyReader {
    public PropertyList read (String path) throws ServiceException {
        Properties props;
        
        if (path == null  || path.equals("")) {
            props = System.getProperties();
        }
        else {
            props = new Properties();
            try {
                InputStream inputStream = PropertyReaderImpl.class.getResourceAsStream(path);
                if (inputStream == null) {
                    throw new ServiceException("property file not found: " + path);
                }
                props.load(inputStream);
            } catch (IOException e) {
                throw new ServiceException(("property file read error: " + path), e);
            }
        }
        
        PropertyList result = new PropertyList();
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            result.add (key, value);
        }
        return result;
    }
}
