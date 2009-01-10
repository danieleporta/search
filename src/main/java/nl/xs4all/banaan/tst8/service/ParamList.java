package nl.xs4all.banaan.tst8.service;

import java.util.Enumeration;

import javax.servlet.ServletContext;

import nl.xs4all.banaan.tst8.util.GenericBindingList;
import nl.xs4all.banaan.tst8.web.WicketApplication;

public class ParamList extends GenericBindingList<String> {

    public ParamList () {
        super();
        WicketApplication app = WicketApplication.get();
        ServletContext sc = app.getServletContext();
        Enumeration<?> e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = sc.getInitParameter(key);
            add (key, value);
        }
    }
}



