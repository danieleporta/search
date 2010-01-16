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
public class AssocList<T> {
    private static final long serialVersionUID = 1L;

    private List<Assoc<T>> list;
    
    public AssocList () {
        list = new LinkedList<Assoc<T>>();
    }
    
    public void add (String key, T value) {
        list.add(new Assoc<T>(key, value));
    }
    
    public List<Assoc<T>> getList() {
        Collections.sort(list, Assoc.<T>comparator());
        return list;
    }

    public T lookup(String wanted) {
        for (Assoc<T> binding : list) {
            String key = binding.getKey();
            if (key.equals(wanted)) {
                return binding.getValue();
            }
        }
        throw new IllegalArgumentException("Key not found: " + wanted);
    }
}
