package nl.xs4all.banaan.tst8.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.wicket.protocol.http.WebApplication;

import nl.xs4all.banaan.tst8.service.ParamReader;
import nl.xs4all.banaan.tst8.util.Assoc;

public class ParamReaderImpl implements ParamReader {

    @SuppressWarnings("unchecked")
    public List<Assoc<String>> read() {
        List<Assoc<String>> result = new ArrayList<Assoc<String>>(); 
        ServletContext sc = WebApplication.get().getServletContext();
        Enumeration<String> e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            result.add (new Assoc<String>(key, sc.getInitParameter(key)));
        }
        Collections.sort(result);
        return result;
    }
}
