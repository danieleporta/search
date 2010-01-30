package nl.xs4all.banaan.tst8.service;

import java.util.Enumeration;

import javax.servlet.ServletContext;

import nl.xs4all.banaan.tst8.util.AssocList;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * List of configuration parameters as provided by the environment,
 * either in web.xml in the war file, or in configuration of the web server.
 * 
 * @author konijn
 *
 */
public class ParamList extends AssocList<String> {

    public ParamList () {
        super();
        WebApplication app = WebApplication.get();
        ServletContext sc = app.getServletContext();
        Enumeration<?> e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = sc.getInitParameter(key);
            add (key, value);
        }
    }
}



