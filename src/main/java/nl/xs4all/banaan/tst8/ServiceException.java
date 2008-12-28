package nl.xs4all.banaan.tst8;

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
}
