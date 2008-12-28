package nl.xs4all.banaan.tst8;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyList {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(PropertyList.class);

    private List<Binding<String>> list;
    
    public PropertyList (Properties props) {
        list = new LinkedList<Binding<String>>();
        init(props);
    }
    
    private void add (String key, String value) {
        logger.debug("adding " + key);
        list.add(new Binding<String>(key, value));
    }

    public List<Binding<String>> getList () {
        return list;
    }

    private void init(Properties props){
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            add (key, value);
        }
    }
}
