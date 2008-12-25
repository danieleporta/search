package nl.xs4all.banaan.tst8;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * thingy to keep key and value together in list view.
 */
public class Binding implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String key;
    private String value;
    
    public Binding (String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * present the bindings as a list.
     * Not some Iterable wrapper, Listview wants a List,
     * and that's final.
     * @param props
     * @return
     */
    public static List<Binding> bindingList (Properties props){
        List<Binding> result = new LinkedList<Binding>();
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String name = (String) e.nextElement();
            String value = props.getProperty(name);
            result.add (new Binding (name, value));
        }
        return Collections.unmodifiableList(result);
    }
 
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
 }
