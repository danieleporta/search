package nl.xs4all.banaan.tst8.web;

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
public class WicketApplication extends WebApplication
{   
    private MenuList menuList;
    
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }
    
    @Override
    protected void init() {
        menuList = new MenuList();
        menuList.add("home", HomePage.class);
        menuList.add("jndi", JndiPage.class);
        menuList.add("param", ParamPage.class);        
        menuList.add("property", PropertyPage.class);
    }
	
    public MenuList getMenuList() {
        return menuList;
    }
    
    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<?> getHomePage() {
	return HomePage.class;
    }
}
