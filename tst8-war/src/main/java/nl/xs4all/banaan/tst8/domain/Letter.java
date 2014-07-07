package nl.xs4all.banaan.tst8.domain;

/**
 * Domain object intended to show composition of panels.
 * @author konijn
 *
 */
public class Letter {
    private final Address from;
    private final Address to;
    private final Long postage;
    
    public Letter(Address from, Address to, Long postage) {
        this.from = from;
        this.to = to;
        this.postage = postage;
    }
    
    public Address getFrom() {
        return from;
    }
    
    public Address getTo() {
        return to;
    }
    
    public Long getPostage() {
        return postage;
    }
}
