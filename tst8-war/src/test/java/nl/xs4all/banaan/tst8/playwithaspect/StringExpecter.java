package nl.xs4all.banaan.tst8.playwithaspect;

/** strings should arrive in order given by constructor */
public class StringExpecter {
    int i = 0;
    private final String[] expected;
    
    public StringExpecter(String ... expected) {
        this.expected = expected;
    }

    public void pass(String actual) {
        if (i >= expected.length) {
            throw new RuntimeException("too much");
        }
        if (! expected[i].equals(actual)) {
            throw new RuntimeException("wrong " + i);
        }
        i++;
    }

    public void done() {
        if (i != expected.length) {
            throw new RuntimeException("not enough actuals");
        }
    }
}
