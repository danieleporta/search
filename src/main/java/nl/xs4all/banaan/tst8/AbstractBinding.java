package nl.xs4all.banaan.tst8;

public abstract class AbstractBinding {

    /**
     * thingy to keep key and value together in list view.
     */

    private String key;
    private String value;

    public AbstractBinding(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
