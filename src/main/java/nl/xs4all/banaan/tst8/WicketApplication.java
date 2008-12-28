package nl.xs4all.banaan.tst8;

import nl.xs4all.banaan.tst8.home.HomePage;
import nl.xs4all.banaan.tst8.jndi.JndiPage;
import nl.xs4all.banaan.tst8.param.ParamPage;
import nl.xs4all.banaan.tst8.property.PropertyPage;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{   
    private MenuList menuList;
    
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }
    
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
	public Class<?> getHomePage()
	{
		return HomePage.class;
	}

}
