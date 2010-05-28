package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Providing H2 database connection via a DBCP connection pool.
 * This pool is not tied to a particular type of database. 
 */
public class H2DbcpConnectionPoolTest {

    @Test
    public void testDataSourceIsLimited() {
        DataSource source = DbcpPoolBuilder.makeDataSourceReadCommited();
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
        DataSource source = DbcpPoolBuilder.makeDataSourceReadCommited();
        int i;
        for (i = 0; i < 10; i++) {
            Connection connection = source.getConnection();
            connection.close();
        }
    }

    @Test
    public void testDataSourceFromFactoryIsLimited() throws Exception {
        DataSource source = DbcpPoolBuilder.makeDataSourceViaFactory();
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

}
