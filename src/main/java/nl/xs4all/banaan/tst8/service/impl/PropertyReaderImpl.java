package nl.xs4all.banaan.tst8.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

/**
 * implement property reader
 * @author konijn
 *
 */
public class PropertyReaderImpl implements PropertyReader {
    
    public List<Assoc<String>> read(String path) throws ServiceException {
        List<Assoc<String>> result = new ArrayList<Assoc<String>>(); 
        for (Entry<Object,Object> entry : getProperties(path).entrySet()) {
            String key = (String) entry.getKey(); 
            String value = (String) entry.getValue();
            result.add (new Assoc<String>(key, value));
        }
        Collections.sort(result, new Comparator<Assoc<String>> () {
            public int compare(Assoc<String> o1, Assoc<String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
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
