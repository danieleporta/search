package nl.xs4all.banaan.tst8.util;

/** Container for good or bad results, almost not completely unlike Haskell */
public class Either<GOOD,BAD> {
    private final GOOD good;
    private final BAD bad;
    private final boolean isGood;
    
    public  static <GOOD,BAD> Either<GOOD,BAD> good(GOOD good) {
        return new Either<GOOD,BAD>(good, null, true);
    }
    
    public  static <GOOD,BAD> Either<GOOD,BAD> bad(BAD bad) {
        return new Either<GOOD,BAD>(null, bad, false);
    }
    
    private Either(GOOD good, BAD bad, boolean isGood) {
        this.good = good;
        this.bad = bad;
        this.isGood = isGood;
    }
    
    public GOOD getGood() {
        if (! isGood) {
            throw new IllegalStateException("nothing good in this either");
        }
        return good;
    }
    
    public BAD getBad() {
        if (isGood) {
            throw new IllegalStateException("nothing bad in this either");
        }
        return bad;
    }
    
    public boolean isGood() {
        return isGood;
    }
}
