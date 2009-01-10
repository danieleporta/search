package nl.xs4all.banaan.tst8.service;

import java.util.Enumeration;
import java.util.Properties;

import nl.xs4all.banaan.tst8.util.GenericBindingList;

public class PropertyList extends GenericBindingList<String> {
    private static final long serialVersionUID = 1L;

    public PropertyList (Properties props) {
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            add (key, value);
        }
    }
}
