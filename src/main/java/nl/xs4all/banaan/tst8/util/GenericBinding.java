package nl.xs4all.banaan.tst8.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A binding says a string and object go together;
 * the type of object is parameterized: it could be a property
 * or a destination for a menu.
 * @author konijn
 *
 * @param <T>
 * The type o the value to be stored in the bindings;
 * key always string.
 */
public class GenericBinding<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;
    private T value;

    public GenericBinding(String key, T value) {
        this.key = key;
        this.value = value;
    }

    /**
     * comparator for use in collections.sort().
     * @param <T>
     * @return
     */
    public static <T> Comparator<GenericBinding<T>> comparator() {
        return new Comparator<GenericBinding<T>> () {
            public int compare(GenericBinding<T> o1, GenericBinding<T> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        };
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
