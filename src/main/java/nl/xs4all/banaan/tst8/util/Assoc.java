package nl.xs4all.banaan.tst8.util;

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
public class Assoc<T> implements Comparable<Assoc<T>>{
    private static final long serialVersionUID = 1L;

    private String key;
    private T value;

    public Assoc(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public int compareTo(Assoc<T> o) {
        return getKey().compareTo(o.getKey());
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
