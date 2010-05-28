package nl.xs4all.banaan.tst8.playwithdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/** example creation of DbcpBool with hardcoded url. */
public class DbcpPoolBuilder {

    private static final String H2_DRIVER = "org.h2.Driver";
    
    // todo: make debug optional
    // private static final String MYDB_URL = "jdbc:h2:mem:mydb;TRACE_LEVEL_SYSTEM_OUT=3";
    private static final String MYDB_URL = "jdbc:h2:mem:mydb";

    public static DataSource makeDataSourceReadCommited() {
        BasicDataSource source = makeDataSourceWithUnspecifiedIsolation();
        source.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return source;
    }

    public static DataSource makeDataSourceReadUncommited() {
        BasicDataSource source = makeDataSourceWithUnspecifiedIsolation();
        source.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        return source;
    }
    
    private static BasicDataSource makeDataSourceWithUnspecifiedIsolation() {
        // In eg Jetty, the DataSource is created directly and added to JNDI
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(DbcpPoolBuilder.H2_DRIVER);
        source.setUrl(DbcpPoolBuilder.MYDB_URL);
        // default unlimited
        source.setMaxOpenPreparedStatements(10);
        // default 8
        source.setMaxActive(8);
        // default 8
        source.setMaxIdle(8);
        source.setUsername("");
        source.setPassword("");
        // setLoginTimeout not supported.
        // wait this in msec when trying to get connection.  <= 0 is forever.
        source.setMaxWait(1);
        return source;
    }

    public static DataSource makeDataSourceViaFactory() throws Exception {
        // In Tomcat, the datasource is created via a factory, 
        // fed by properties stored next to the factory in JNDI.
        // the actual construction is then done with
        // factory.getObjectInstance(obj, name, nameCtx, environment);
        // see http://tomcat.apache.org/tomcat-4.1-doc/printer/jndi-datasource-examples-howto.html
        
        Properties properties = new Properties();
        properties.put("driverClassName", DbcpPoolBuilder.H2_DRIVER);
        properties.put("url", DbcpPoolBuilder.MYDB_URL);
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
    
    public static void shutdownDatabaseAndDropAllTables() throws SQLException {
        DataSource dataSource = makeDataSourceReadCommited();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("shutdown immediately");
        // it seems the close waits for the execute that did something in background.
        statement.close();
        connection.close();
    }


}
