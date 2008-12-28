package nl.xs4all.banaan.tst8;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;

public class MenuList {
    private static final long serialVersionUID = 1L;

    private List<GenericBinding<Class<? extends WebPage>>> list;
    
    public MenuList () {
        list = new LinkedList<GenericBinding<Class<? extends WebPage>>>();
    }
    
    public void add (String key, Class<? extends WebPage> pageClass) {
        list.add(new GenericBinding<Class<? extends WebPage>>(key, pageClass));
    }
    
    public List<GenericBinding<Class<? extends WebPage>>> getList () {
        return list;
    }
}
