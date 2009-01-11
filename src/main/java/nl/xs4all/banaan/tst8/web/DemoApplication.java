package nl.xs4all.banaan.tst8.web;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.JndiReaderImpl;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;
import nl.xs4all.banaan.tst8.web.menu.MenuList;
import nl.xs4all.banaan.tst8.web.param.ParamPage;
import nl.xs4all.banaan.tst8.web.property.PropertyPage;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. 
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class DemoApplication extends WebApplication
{   
    private MenuList menuList;
    
    /**
     * The reasder that will talk to JNDI; to be made pluggable via spring.
     */
    private JndiReader jndiReader;
    
    public static DemoApplication get() {
        return (DemoApplication) Application.get();
    }
    
    @Override
    protected void init() {
        menuList = new MenuList();
        menuList.add("home", HomePage.class);
        menuList.add("jndi", JndiPage.class);
        menuList.add("param", ParamPage.class);        
        menuList.add("property", PropertyPage.class);
        jndiReader = new JndiReaderImpl();
    }
	
    public MenuList getMenuList() {
        return menuList;
    }
    
  
    public void setJndiReader(JndiReader jndiReader) {
	this.jndiReader = jndiReader;
    }

    public JndiReader getJndiReader() {
	return jndiReader;
    }

    
    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<?> getHomePage() {
	return HomePage.class;
    }
}
