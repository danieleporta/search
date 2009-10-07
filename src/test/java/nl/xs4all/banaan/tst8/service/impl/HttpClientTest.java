package nl.xs4all.banaan.tst8.service.impl;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Before;
import org.junit.Test;

/**
 * Try using the Apache HttpClient in combination with a fake connection.
 * @author konijn
 *
 */
public class HttpClientTest {
    
    private HashMap<String,String> map;


    @Before
    public void setUp() {
        map = new HashMap<String, String>();
    }
  
    @Test
    public void testDeclareClient() {
        new HttpClient();
    }

    @Test
    public void testDeclareMethod() throws HttpException, IOException {
        new HttpClient();
        new GetMethod("http://localhost/index.html");
        // client.executeMethod(method);
    }
    
    @Test
    public void testDeclareWithConnectionManager() throws HttpException, IOException {
        HttpConnectionManager manager = new SimpleHttpConnectionManager(true);
        new HttpClient(manager);
        new GetMethod("http://localhost/index.html");
        // client.executeMethod(method);
    }
    
    @Test
    public void testDeclareWithTestConnectionManager() throws HttpException, IOException {
        HttpConnectionManager manager = new TestConnectionManager(true, map);
        HttpClient client = new HttpClient(manager);
        GetMethod method = new GetMethod("http://localhost/index.html");
        client.executeMethod(method);

        assertEquals("localhost", map.get("host"));
        assertEquals("http://localhost", map.get("url"));
        assertEquals("with timeout and long name", map.get("call"));
        
        assertEquals(2050, method.getResponseContentLength());
        method.releaseConnection();
    }


}
