package nl.xs4all.banaan.tst8.service;

/**
 * Centralised access to services.
 * @author konijn
 *
 */
public class Services {
    /**
     * services injected via spring
     */
    private JndiReader jndiReader;
    private Notificator notificator;
    private PropertyReader propertyReader;
    
    public void setJndiReader(JndiReader jndiReader) {
        this.jndiReader = jndiReader;
    }
    
    public JndiReader getJndiReader() {
        return jndiReader;
    }

    public void setNotificator(Notificator notificator) {
        this.notificator = notificator;
    }
    
    public Notificator getNotificator() {
        return notificator;
    }
    
    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }
    
    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
}
