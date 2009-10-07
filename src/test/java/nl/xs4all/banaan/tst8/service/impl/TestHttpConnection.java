package nl.xs4all.banaan.tst8.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpParser;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.junit.Ignore;


/**
 * Version of Http Connection to support snooping & faking.
 * 
 * Since HttpConnection is not defined by an interface, we're forced to extend
 * & ignore the underlying layer.
 * 
 * @author konijn
 * 
 */
@Ignore
public class TestHttpConnection extends HttpConnection {
    private static final Logger logger = Logger.getLogger(TestHttpConnection.class);
    
    private String proxyHost;   // null if unused
    private int proxyPort;      // -1 if unused
    private String host;
    private int port;
    private Protocol protocol;
    
    /** stream not available */
    private boolean isOpen = false;
    
    /** may reuse the connection after its been released. */
    private HttpConnectionManager httpConnectionManager;

    /** tree of settings */
    private HttpConnectionParams params;

    /** result of the last open */
    private InputStream inputStream;

    /** the resource that will be returned by this connection */
    private String resourceName;
    
   
    

    public TestHttpConnection(HostConfiguration hostConfiguration, long timeout, String resourceName) {
        this(hostConfiguration.getProxyHost(),
                hostConfiguration.getProxyPort(),
                hostConfiguration.getHost(), 
                hostConfiguration.getPort(), 
                hostConfiguration.getProtocol(),
                resourceName);
        
    }
    
    public TestHttpConnection(
            String proxyHost,
            int proxyPort,
            String host,
            int port,
            Protocol protocol,
            String resourceName) {
        super(proxyHost,proxyPort,host,port,protocol);
        logger.debug("constructor start");
        setProxyHost(proxyHost);
        setProxyPort(proxyPort);
        setHost(host);
        setProtocol(protocol);
        setPort(getProtocol().resolvePort(port));
        params = new HttpConnectionParams();
        this.resourceName = resourceName;
    }

    // used in HttpMethodDirector.executeWithRetry on IOException
    // closes both input and output.
    @Override
    public void close() {
        logger.debug("close");
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new IllegalStateException("could not close input stream");
        }
        isOpen = false;
    }

    // used in HttpMethodDirector.executeWithRetry.
    // real version tries if reading still works.
    @Override
    public boolean closeIfStale() throws IOException {
        logger.debug("closeIfStale");
        if (isOpen()) {
            close();
            return true;
        }
        return false;
    }

    // used in writeRequest
    // does outputStream.flush();
    @Override
    public void flushRequestOutputStream() throws IOException {
        logger.debug("flushRequestOutputStream");
    }

    // used by GetMethod.addHostRequestHeader
    @Override
    public String getHost() {
        logger.debug("getHost - " + host);
        return host;
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
        logger.debug("getParams - " + params);
        return params;
    }

    // used by GetMethod.addHostRequestHeader
    @Override
    public int getPort() {
        logger.debug("getPort - " + port);
        return port;
    }

    // used by GetMethod.addHostRequestHeader
    @Override
    public Protocol getProtocol() {
        logger.debug("getProtocol start - " + protocol);
        return protocol;
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
        logger.debug("getResponseInputStream");
        return inputStream;
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

    // used after closeIfStale
    @Override
    public boolean isOpen() {
        logger.debug("isOpen - " + isOpen);
        return isOpen;
    }

    @Override
    public boolean isProxied() {
        boolean result = (proxyHost != null);
        logger.debug("isProxied - " + result);
        return result;
    }

    @Override
    public boolean isResponseAvailable() throws IOException {
        boolean result;
        if (this.isOpen) {
            result = this.inputStream.available() > 0;
        } else {
            result = false;
        }
        logger.debug("isResponseAvailable " + result);
        return result;
    }

    @Override
    public boolean isResponseAvailable(int timeout) throws IOException {
        punt();
        return super.isResponseAvailable(timeout);
    }

    // used in open() to determine socket factory
    @Override
    public boolean isSecure() {
        boolean result = protocol.isSecure();
        logger.debug("isSecure - " + result);        
        return result;
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
        boolean result = !isProxied();
        logger.debug("isTransparent - " + result);
        return result;
    }

    // creates inputStream, outputStream to socket.
    @Override
    public void open() throws IOException {
        logger.debug("open");
        if (isOpen) {
            throw new IllegalStateException("opening twice on same connection");
        }

        inputStream = getClass().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalStateException("cannot open resource stream for: " + resourceName);            
        }
        isOpen = true;
    }

    
    // used in httpMethodBase.writeRequestLine
    // does encoding, then write
    // TODO: consider logging this.
    @Override
    public void print(String data, String charset) throws IOException,
            IllegalStateException {
        logger.debug("print - data: " + data);
        logger.debug("print - charset: " + charset);
    }

    @Override @Deprecated
    public void print(String data) throws IOException, IllegalStateException {
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
    @Override
    public String readLine(String charset) throws IOException,
            IllegalStateException {
        logger.debug("readLine - charset " + charset);
        return HttpParser.readLine(inputStream, charset);
    }

    // used in executeMethod (the finally part)
    @Override
    public void releaseConnection() {
        logger.debug("releaseConnection");
        // TODO: consider locking
        if (httpConnectionManager != null) {
            logger.debug("releaseConnection has manager - " + httpConnectionManager);
            httpConnectionManager.releaseConnection(this);
        }
    }

    @Override @Deprecated
    public void setConnectionTimeout(int timeout) {
        punt();
        super.setConnectionTimeout(timeout);
    }

    @Override
    public void setHost(String host) throws IllegalStateException {
        logger.debug("setHost - " + host);
        if (host == null) {
            throw new IllegalStateException("Null host");
        }
        this.host = host;
    }

    @Override
    public void setHttpConnectionManager(
            HttpConnectionManager httpConnectionManager) {
        // TODO: consider locking.
        logger.debug("setHttpConnectionManager - " + httpConnectionManager);
        this.httpConnectionManager = httpConnectionManager;
    }

    // used in httpMethodBase.execute()
    // a real connection may wrap the socket stream in another stream
    // that handles chunking.  That wrapper needs to be remembered to
    // allow closing it later.  We don't do encodings, so ignore this.
    @Override
    public void setLastResponseInputStream(InputStream inStream) {
        logger.debug("setLastResponseInputStream - " + inStream);
    }

    @Override
    public void setLocalAddress(InetAddress localAddress) {
        punt();
    }

    @Override
    public void setParams(HttpConnectionParams params) {
        punt();
    }

    @Override
    public void setPort(int port) throws IllegalStateException {
        logger.debug("setPort start - " + port);
        this.port = port;
    }

    @Override
    public void setProtocol(Protocol protocol) {
        logger.debug("setProtocol start - " + protocol);
        if (protocol == null) {
            throw new IllegalArgumentException("Null protocol");
        }
        this.protocol = protocol;
    }

    @Override
    public void setProxyHost(String proxyHost) throws IllegalStateException {
        logger.debug("setProxyHost start - " + proxyHost);
        this.proxyHost = proxyHost;
    }

    @Override
    public void setProxyPort(int proxyPort) throws IllegalStateException {
        logger.debug("setProxyPort start - " + proxyPort);
        this.proxyPort = proxyPort;
    }

    @Override @Deprecated
    public void setSendBufferSize(int sendBufferSize) throws SocketException {
        punt();
    }

    // happens after doing open.
    @Override
    public void setSocketTimeout(int timeout) throws SocketException,
            IllegalStateException {
        logger.debug("setSocketTimeout - " + timeout);
    }

    @Override @Deprecated
    public void setSoTimeout(int timeout) throws SocketException,
            IllegalStateException {
        punt();
    }

    @Override @Deprecated
    public void setStaleCheckingEnabled(boolean staleCheckEnabled) {
        punt();
    }

    @Override @Deprecated
    public void setVirtualHost(String host) throws IllegalStateException {
        punt();
    }

    @Override @Deprecated
    public void shutdownOutput() {
        punt();
    }

    @Override
    public void tunnelCreated() throws IllegalStateException, IOException {
        punt();
    }

    // called from write
    @Override
    public void write(byte[] data, int offset, int length) throws IOException,
            IllegalStateException {
        punt();
    }

    // called from print
    // does this.write(data, 0, data.length);
    @Override
    public void write(byte[] data) throws IOException, IllegalStateException {
        punt();
    }

    // used in GetMethod.writeRequest
    // writes "\r\n".getBytes()
    @Override
    public void writeLine() throws IOException, IllegalStateException {
        logger.debug("writeLine");
    }

    @Override
    public void writeLine(byte[] data) throws IOException,
            IllegalStateException {
        punt();
    }

    private static void punt() {
        throw new IllegalStateException("Not implemented");
    }

}
