package nl.xs4all.banaan.tst8.wiring;



import org.junit.Ignore;

import com.google.inject.Module;


/**
 * WicketApplicatonFactory wired for test use.
 * @author konijn
 *
 */
@Ignore
public class TestWebApplicationFactory extends BaseWebApplicationFactory {
    @Override
    public Module getModule() {
        return new TestApplicationModule();
    }
}
