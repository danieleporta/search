package nl.xs4all.banaan.tst8.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map.Entry;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;

/**
 * implement property reader
 * @author konijn
 *
 */
public class PropertyReaderImpl implements PropertyReader {
    
    public PropertyList read(String path) throws ServiceException {
        PropertyList result = new PropertyList();
        for (Entry<Object,Object> entry : getProperties(path).entrySet()) {
            String key = (String) entry.getKey(); 
            String value = (String) entry.getValue();
            result.add (key, value);
        }
        return result;
    }

    private Properties getProperties(String path) throws ServiceException {
        if (path == null  || path.equals("")) {
            return System.getProperties();
        }
        try {
            return readPropertiesFromPropertyResource(path);
        } catch (IOException e) {
            throw new ServiceException(("property file read error: " + path), e);
        }
    }

    private Properties readPropertiesFromPropertyResource(String path)
            throws IOException, ServiceException {
        Properties props = new Properties();
        props.load(getInputStream(path));
        return props;
    }

    private InputStream getInputStream(String path) throws ServiceException {
        InputStream inputStream = PropertyReaderImpl.class.getResourceAsStream(path);
        if (inputStream == null) {
            throw new ServiceException("property file not found: " + path);
        }
        return inputStream;
    }
}
