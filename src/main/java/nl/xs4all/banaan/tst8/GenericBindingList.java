package nl.xs4all.banaan.tst8;

import java.util.LinkedList;
import java.util.List;

public class GenericBindingList<T> {
    private static final long serialVersionUID = 1L;

    private List<GenericBinding<T>> list;
    
    public GenericBindingList () {
        list = new LinkedList<GenericBinding<T>>();
    }
    
    public void add (String key, T value) {
        list.add(new GenericBinding<T>(key, value));
    }
    
    public List<GenericBinding<T>> getList () {
        return list;
    }
    
}
