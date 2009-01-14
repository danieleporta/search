package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

/**
 * Helper to cause an exception.
 * Our error handling model:
 *  -- services can raise exception;
 *  -- basepage web layer logs this
 *  -- and reraises exception;
 *  -- now application shows error page.
 *
 * Note that basepage cannot just set repsonse page;
 * you need an exception to bypass the enclosing blocks
 * and avoid errors in rendering there because the service
 * did not provide a workable model.
 * 
 * An alternative would be to override newWebRequestCycle
 * in the application, and in onRuntimeException provide
 * an error page.
 *  
 * @author konijn
 *
 */
public class ThrowingPage extends BasePage {
    ServiceException exception;
    public ThrowingPage (ServiceException exception) {
        this.exception = exception;
        init();
    }
    
    @Override
    public void doInit () throws ServiceException {
        if (exception != null) {
            throw exception;
        }
        throw new RuntimeException ("gotcha!");
    }
}