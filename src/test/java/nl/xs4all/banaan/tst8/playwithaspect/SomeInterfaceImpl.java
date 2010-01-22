package nl.xs4all.banaan.tst8.playwithaspect;

import org.junit.Ignore;

/** Some implementation; we want to test its interceptor */
@Ignore
public class SomeInterfaceImpl implements SomeInterface {

    public String run(String arg1, String arg2) {
        System.out.println("impl");
        return arg1 + arg2;
    }

}
