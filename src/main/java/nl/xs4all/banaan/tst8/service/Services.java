package nl.xs4all.banaan.tst8.service;


public interface Services {

    public abstract JndiReader getJndiReader();

    public abstract Notificator getNotificator();

    public abstract PropertyReader getPropertyReader();

    public abstract BuildInfo getBuildInfo();

}
