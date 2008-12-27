package nl.xs4all.banaan.tst8;

import nl.xs4all.banaan.tst8.home.HomePage;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }
	
	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class<?> getHomePage()
	{
		return HomePage.class;
	}

}
