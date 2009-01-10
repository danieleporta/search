package nl.xs4all.banaan.tst8;

/**
 * A binding says a string and object go together;
 * the type of object is parameterized: it could be a property
 * or a destination for a menu.
 * @author konijn
 *
 * @param <T>
 */
public class GenericBinding<T> {
    private static final long serialVersionUID = 1L;

    private String key;
    private T value;

    public GenericBinding(String key, T value) {
        this.key = key;
        this.value = value;
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
