package nl.xs4all.banaan.tst8.service;

/**
 * Centralised access to services.
 * @author konijn
 *
 */
public interface Services {
    public JndiReader getJndiReader();
    public Notificator getNotificator();
    public PropertyReader getPropertyReader();
    public BuildInfo getBuildInfo();
}
