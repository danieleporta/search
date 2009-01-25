package nl.xs4all.banaan.tst8.service;

/**
 * Centralised access to services.
 * @author konijn
 *
 */
public class ServicesImpl implements Services {
    /**
     * services injected via spring
     */
    private JndiReader jndiReader;
    private Notificator notificator;
    private PropertyReader propertyReader;
    
    public void setJndiReader(JndiReader jndiReader) {
        this.jndiReader = jndiReader;
    }
    
    /* (non-Javadoc)
     * @see nl.xs4all.banaan.tst8.service.Services#getJndiReader()
     */
    public JndiReader getJndiReader() {
        return jndiReader;
    }

    public void setNotificator(Notificator notificator) {
        this.notificator = notificator;
    }
    
    /* (non-Javadoc)
     * @see nl.xs4all.banaan.tst8.service.Services#getNotificator()
     */
    public Notificator getNotificator() {
        return notificator;
    }
    
    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }
    
    /* (non-Javadoc)
     * @see nl.xs4all.banaan.tst8.service.Services#getPropertyReader()
     */
    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
}
