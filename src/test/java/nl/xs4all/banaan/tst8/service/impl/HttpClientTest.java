package nl.xs4all.banaan.tst8.service.impl;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
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
    
    
    /**
     * A connection manager that will return snooping connections,
     * and later dummy connections.
     * @author konijn
     *
     */
    public static class TestConnectionManager extends SimpleHttpConnectionManager {

        private HashMap<String, String> map;
        private TestHttpConnection httpConnection;
        private HttpConnectionManagerParams params = new HttpConnectionManagerParams(); 

        public TestConnectionManager(boolean b, HashMap<String,String> map) {
            super(b);
            this.map = map;
            httpConnection = null;
        }

        @Override
        public HttpConnection getConnection(HostConfiguration hostConfiguration) {
            throw new IllegalStateException("Not supported getConnection");
        }

        @Override
        public HttpConnection getConnection(
                HostConfiguration hostConfiguration, long timeout) {
            throw new IllegalStateException("not supported and deprecated getConnection");
        }

        /* (non-Javadoc)
         * @see org.apache.commons.httpclient.SimpleHttpConnectionManager#getConnectionWithTimeout(org.apache.commons.httpclient.HostConfiguration, long)
         */
        @Override
        public HttpConnection getConnectionWithTimeout(
                HostConfiguration hostConfiguration, long timeout) {
            map.put("host", hostConfiguration.getHost());
            map.put("url", hostConfiguration.getHostURL());
            map.put("call", "with timeout and long name");
            httpConnection = new TestHttpConnection(hostConfiguration, timeout);
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
    
    /**
     * Http Connection to support snooping & faking.
     * @author konijn
     *
     */
    public static class WrapperTestHttpConnection extends HttpConnection {

        public WrapperTestHttpConnection(HttpConnection connection) {
            super(connection.getHost(), connection.getPort(), connection.getProtocol());
        }

        // used in HttpMethodDirector.executeWithRetry on IOException
        // closes both input and output.
        @Override
        public void close() {
            super.close();
        }

        // used in HttpMethodDirector.executeWithRetry
        @Override
        public boolean closeIfStale() throws IOException {
            return super.closeIfStale();
        }

        // used in writeRequest
        // does  outputStream.flush();
        @Override
        public void flushRequestOutputStream() throws IOException {
            super.flushRequestOutputStream();
        }

        // used by GetMethod.addHostRequestHeader
        @Override
        public String getHost() {
            return super.getHost();
        }

        @Override
        public HttpConnectionManager getHttpConnectionManager() {
            punt();
            return super.getHttpConnectionManager();
        }

        @Override
        public InputStream getLastResponseInputStream() {
            punt();
            return super.getLastResponseInputStream();
        }

        @Override
        public InetAddress getLocalAddress() {
            punt();
            return super.getLocalAddress();
        }

        // needed for eg staleCheckingEnabled, tcpNoDelay, soTimeout
        @Override
        public HttpConnectionParams getParams() {
            return super.getParams();
        }

        // used by GetMethod.addHostRequestHeader
        @Override
        public int getPort() {
            return super.getPort();
        }
        
        // used by GetMethod.addHostRequestHeader
        @Override
        public Protocol getProtocol() {
            return super.getProtocol();
        }

        @Override
        public String getProxyHost() {
            punt();
            return super.getProxyHost();
        }

        @Override
        public int getProxyPort() {
            punt();
            return super.getProxyPort();
        }

        @Override
        public OutputStream getRequestOutputStream() throws IOException,
                IllegalStateException {
            punt();
            return super.getRequestOutputStream();
        }

        // used in readResponseHeaders.
        @Override
        public InputStream getResponseInputStream() throws IOException,
                IllegalStateException {
            return super.getResponseInputStream();
        }

        @Override
        public int getSendBufferSize() throws SocketException {
            punt();
            return super.getSendBufferSize();
        }

        @Override @Deprecated
        public int getSoTimeout() throws SocketException {
            punt();
            return super.getSoTimeout();
        }

        @Override @Deprecated
        public String getVirtualHost() {
            punt();
            return super.getVirtualHost();
        }

        @Override
        public boolean isOpen() {
            // used after closeIfStale
            return super.isOpen();
        }

        @Override
        public boolean isProxied() {
            return false;
        }

        @Override
        public boolean isResponseAvailable() throws IOException {
            punt();
            return super.isResponseAvailable();
        }

        @Override
        public boolean isResponseAvailable(int timeout) throws IOException {
            punt();
            return super.isResponseAvailable(timeout);
        }

        @Override
        public boolean isSecure() {
            // used in open() to determine socket factory
            return super.isSecure();
        }

        @Override @Deprecated
        public boolean isStaleCheckingEnabled() {
            punt();
            return super.isStaleCheckingEnabled();
        }

        // used in httpMethodBase.generatedRequestLine
        // "not proxied or tunneled" 
        @Override
        public boolean isTransparent() {
            return super.isTransparent();
        }

        @Override
        public void open() throws IOException {
            // creates inputStream, outputStream to socket.
            super.open();
        }

        // used in httpMethodBase.writeRequestLine
        // does encoding, then write
        @Override
        public void print(String data, String charset) throws IOException,
                IllegalStateException {
System.out.println("Data: " + data);
System.out.println("Charset: " + charset);
            super.print(data, charset);
        }

        @Override @Deprecated
        public void print(String data) throws IOException,
                IllegalStateException {
            punt();
            super.print(data);
        }

        @Override
        public void printLine() throws IOException, IllegalStateException {
            punt();
            super.printLine();
        }

        @Override
        public void printLine(String data, String charset) throws IOException,
                IllegalStateException {
            punt();
            super.printLine(data, charset);
        }

        @Override @Deprecated
        public void printLine(String data) throws IOException,
                IllegalStateException {
            punt();
            super.printLine(data);
        }

        @Override @Deprecated
        public String readLine() throws IOException, IllegalStateException {
            punt();
            return super.readLine();
        }

        // used in GetMethod.readStatusLine
        // does HttpParser.readLine(inputStream, charset);
        @Override
        public String readLine(String charset) throws IOException,
                IllegalStateException {
            return super.readLine(charset);
        }

        // used in executeMethod (the finally part)
        @Override
        public void releaseConnection() {
            // TODO: need to release contained connection here.
            // super.releaseConnection();
        }

        @Override @Deprecated
        public void setConnectionTimeout(int timeout) {
            punt();
            super.setConnectionTimeout(timeout);
        }

        @Override
        public void setHost(String host) throws IllegalStateException {
            punt();
            super.setHost(host);
        }

        @Override
        public void setHttpConnectionManager(
                HttpConnectionManager httpConnectionManager) {
            super.setHttpConnectionManager(httpConnectionManager);
        }

        // used in httpMethodBase.execute()
        @Override
        public void setLastResponseInputStream(InputStream inStream) {
            super.setLastResponseInputStream(inStream);
        }

        @Override
        public void setLocalAddress(InetAddress localAddress) {
            punt();
            super.setLocalAddress(localAddress);
        }

        @Override
        public void setParams(HttpConnectionParams params) {
            punt();
            super.setParams(params);
        }

        @Override
        public void setPort(int port) throws IllegalStateException {
            punt();
            super.setPort(port);
        }

        @Override
        public void setProtocol(Protocol protocol) {
            punt();
            super.setProtocol(protocol);
        }

        @Override
        public void setProxyHost(String host) throws IllegalStateException {
            punt();
            super.setProxyHost(host);
        }

        @Override
        public void setProxyPort(int port) throws IllegalStateException {
            punt();
            super.setProxyPort(port);
        }

       @Override @Deprecated
        public void setSendBufferSize(int sendBufferSize)
                throws SocketException {
            punt();
            super.setSendBufferSize(sendBufferSize);
        }

        @Override
        public void setSocketTimeout(int timeout) throws SocketException,
                IllegalStateException {
            // happens after doing open.
            super.setSocketTimeout(timeout);
        }

        @Override @Deprecated
        public void setSoTimeout(int timeout) throws SocketException,
                IllegalStateException {
            punt();
            super.setSoTimeout(timeout);
        }

        @Override @Deprecated
        public void setStaleCheckingEnabled(boolean staleCheckEnabled) {
            punt();
            super.setStaleCheckingEnabled(staleCheckEnabled);
        }

        @Override @Deprecated
        public void setVirtualHost(String host) throws IllegalStateException {
            punt();
            super.setVirtualHost(host);
        }

        @Override @Deprecated
        public void shutdownOutput() {
            punt();
            super.shutdownOutput();
        }

        @Override
        public void tunnelCreated() throws IllegalStateException, IOException {
            punt();
            super.tunnelCreated();
        }

        // called from write
        @Override
        public void write(byte[] data, int offset, int length)
                throws IOException, IllegalStateException {
            super.write(data, offset, length);
        }

        // called from print
        // does this.write(data, 0, data.length);
       @Override
        public void write(byte[] data) throws IOException,
                IllegalStateException {
            super.write(data);
        }

        // used in GetMethod.writeRequest
        // writes "\r\n".getBytes()
        @Override
        public void writeLine() throws IOException, IllegalStateException {
            super.writeLine();
        }

        @Override
        public void writeLine(byte[] data) throws IOException,
                IllegalStateException {
            punt();
            super.writeLine(data);
        }


        
    }
    
    private static void punt() {
        throw new IllegalStateException("Not implemented");
    }

}
