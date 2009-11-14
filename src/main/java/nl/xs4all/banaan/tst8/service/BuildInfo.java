package nl.xs4all.banaan.tst8.service;

/**
 * The build procedure stores identification in a property file.
 * This is encapsulated in a BuildInfo object suitable for injection.
 * We can then render this object in a BuildInfoPanel for inclusion
 * in the footer of the page. 
 * @author konijn
 *
 */
public interface BuildInfo {
    public String getName();
    public String getGroup();
    public String getVersion();
    public String getUser();
}
