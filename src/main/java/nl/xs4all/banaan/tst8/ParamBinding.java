package nl.xs4all.banaan.tst8;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class ParamBinding extends AbstractBinding implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ParamBinding.class);

    public ParamBinding (String key, String value) {
        super(key,value);
    }

    public static List<ParamBinding> list (){
        List<ParamBinding> result = new LinkedList<ParamBinding>();
       
        WicketApplication app = WicketApplication.get();
        ServletContext sc = app.getServletContext();
        Enumeration<?> e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            logger.debug("adding element" + key);
            String value = sc.getInitParameter(key);
            result.add (new ParamBinding (key, value));
        }
        return Collections.unmodifiableList(result);
    }
}

