package nl.xs4all.banaan.tst8;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class ParamList {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ParamList.class);

    private List<Binding<String>> list;
    
    public ParamList () {
        list = new LinkedList<Binding<String>>();
        init();
    }
    
    private void add (String key, String value) {
        list.add(new Binding<String>(key, value));
    }
    
    public List<Binding<String>> getList () {
        return list;
    }
    
    private void init (){
        WicketApplication app = WicketApplication.get();
        ServletContext sc = app.getServletContext();
        Enumeration<?> e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            logger.debug("adding element " + key);
            String value = sc.getInitParameter(key);
            add (key, value);
        }
    }
}



