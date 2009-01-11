package nl.xs4all.banaan.tst8.service;

/**
 * Exception in terms relevant to the application
 * @author konijn
 *
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException (String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException (String message) {
        super(message);
    }
}
