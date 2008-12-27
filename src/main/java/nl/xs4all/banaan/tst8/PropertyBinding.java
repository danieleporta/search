package nl.xs4all.banaan.tst8;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;



public class PropertyBinding extends AbstractBinding implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyBinding (String key, String value) {
        super(key,value);
    }
    
    /**
     * present the bindings as a list.
     * Not some Iterable wrapper, Listview wants a List,
     * and that's final.
     * @param props
     * @return
     */
    public static List<PropertyBinding> bindingList (Properties props){
        List<PropertyBinding> result = new LinkedList<PropertyBinding>();
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String name = (String) e.nextElement();
            String value = props.getProperty(name);
            result.add (new PropertyBinding (name, value));
        }
        return Collections.unmodifiableList(result);
    }
}

