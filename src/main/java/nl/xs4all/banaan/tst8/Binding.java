package nl.xs4all.banaan.tst8;

/**
 * Can we do a strongly typed binding for both menus and jndi?
 * @author konijn
 *
 * @param <T>
 */
public class Binding<T> {
    private static final long serialVersionUID = 1L;

    private String key;
    private T value;

    public Binding(String key, T value) {
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
