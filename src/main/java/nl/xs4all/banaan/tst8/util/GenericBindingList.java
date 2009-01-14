package nl.xs4all.banaan.tst8.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Bindinglist maintains key/value pairs in a list that is
 * convenient for wicket to display.
 * @author konijn
 *
 * @param <T>
 * The type of the value in individual bindings.
 */
public class GenericBindingList<T> {
    private static final long serialVersionUID = 1L;

    private List<GenericBinding<T>> list;
    
    public GenericBindingList () {
        list = new LinkedList<GenericBinding<T>>();
    }
    
    public void add (String key, T value) {
        list.add(new GenericBinding<T>(key, value));
    }
    
    public List<GenericBinding<T>> getList() {
        Collections.sort(list, GenericBinding.<T>comparator());
        return list;
    }
}
