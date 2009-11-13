package nl.xs4all.banaan.tst8.wiring;


import com.google.inject.Module;

/**
 * WicketApplicatonFactory wired for production use.
 * @author konijn
 *
 */
public class ProductionWebApplicationFactory extends BaseWebApplicationFactory {
    @Override
    public Module getModule() {
        return new ProductionApplicationModule();
    }
}
