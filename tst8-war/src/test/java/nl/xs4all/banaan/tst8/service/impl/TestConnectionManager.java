package nl.xs4all.banaan.tst8.service.impl;

import java.util.HashMap;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.junit.Ignore;

/**
 * Connection manager for use in {@link HttpClient} that returns responses from
 * a property file instead of from a socket connection.
 * 
 * This connection manager extends the default connection manager.
 * 
 * @author konijn
 * 
 */
@Ignore
public class TestConnectionManager extends SimpleHttpConnectionManager {

    /** map provides shared data between creator and this for test purposes */
    private HashMap<String, String> map;
    
    /** current connection; there can be only one */
    private TestHttpConnection httpConnection;
    
    private HttpConnectionManagerParams params = new HttpConnectionManagerParams();

    /** constructor */
    public TestConnectionManager(boolean b, HashMap<String, String> map) {
        super(b);
        this.map = map;
        httpConnection = null;
    }

    @Override
    public HttpConnection getConnection(HostConfiguration hostConfiguration) {
        throw new IllegalStateException("Not supported getConnection");
    }

    @Override
    public HttpConnection getConnection(HostConfiguration hostConfiguration,
            long timeout) {
        throw new IllegalStateException(
                "not supported and deprecated getConnection");
    }

    /**
     * Return connection that provides answers not from socket but
     * from resource file.
     * 
     */
    @Override
    public HttpConnection getConnectionWithTimeout(
            HostConfiguration hostConfiguration, long timeout) {
        map.put("host", hostConfiguration.getHost());
        map.put("url", hostConfiguration.getHostURL());
        map.put("call", "with timeout and long name");
        if (httpConnection != null) {
            throw new IllegalStateException("cant get connection before releasing last one");
        }
        httpConnection = new TestHttpConnection(hostConfiguration, timeout, "/http/response1.txt");
        httpConnection.setHttpConnectionManager(this);
        httpConnection.getParams().setDefaults(this.params);
        return httpConnection;
    }

    @Override
    public void releaseConnection(HttpConnection conn) {
        if (conn != httpConnection) {
            throw new IllegalArgumentException("unknown connection releases");
        }
        conn.close();
        httpConnection = null;
    }

    @Override
    public void setParams(HttpConnectionManagerParams params) {
        throw new IllegalStateException("Not supported setParams");
    }

}
