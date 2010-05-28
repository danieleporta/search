package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

/**
 * Providing H2 database connection via a DBCP connection pool.
 * This pool is not tied to a particular type of database. 
 */
public class H2DbcpConnectionPoolTest {

    private static final String MYDB_URL = "jdbc:h2:mem:mydb";
    private static final String H2_DRIVER = "org.h2.Driver";

    @Test
    public void testDataSourceIsLimited() {
        DataSource source = makeDataSource();
        int i = 0;
        try {
            while (true) {
                source.getConnection();
                i++;
            }
        }
        catch (Exception e) {
            Assert.assertEquals(8, i);
        }
    }
    
    public void testConnectionsCanBeReused() throws Exception {
        DataSource source = makeDataSource();
        int i;
        for (i = 0; i < 10; i++) {
            Connection connection = source.getConnection();
            connection.close();
        }
    }

    private DataSource makeDataSource() {
        // In eg Jetty, the DataSource is created directly and added to JNDI
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(H2_DRIVER);
        source.setUrl(MYDB_URL);
        // default unlimited
        source.setMaxOpenPreparedStatements(10);
        // default 8
        source.setMaxActive(8);
        // default 8
        source.setMaxIdle(8);
        source.setUsername("");
        source.setPassword("");
        source.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        // setLoginTimeout not supported.
        // wait this in msec when trying to get connection.  <= 0 is forever.
        source.setMaxWait(1);
        return source;
    }

    @Test
    public void testDataSourceFromFactoryIsLimited() throws Exception {
        DataSource source = makeDataSourceViaFactory();
        int i = 0;
        try {
            while (true) {
                source.getConnection();
                i++;
            }
        }
        catch (Exception e) {
            Assert.assertEquals(8, i);
        }
    }

    private DataSource makeDataSourceViaFactory() throws Exception {
        // In Tomcat, the datasource is created via a factory, 
        // fed by properties stored next to the factory in JNDI.
        // the actual construction is then done with
        // factory.getObjectInstance(obj, name, nameCtx, environment);
        // see http://tomcat.apache.org/tomcat-4.1-doc/printer/jndi-datasource-examples-howto.html
        
        Properties properties = new Properties();
        properties.put("driverClassName", H2_DRIVER);
        properties.put("url", MYDB_URL);
        properties.put("username", "");
        properties.put("password", "");
        properties.put("maxOpenPreparedStatements", "10");
        properties.put("maxActive", "8");
        properties.put("maxIdle", "8");
        properties.put("defaultTransactionIsolation", "2"); // read commited        
        properties.put("maxWait", "1");
        // setLoginTimeout not supported.
        // wait this in msec when trying to get connection.  <= 0 is forever.
        return BasicDataSourceFactory.createDataSource(properties);
    }

}
