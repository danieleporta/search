package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * H2 can provide connections via a datasource, which is what containers
 * normally provide via JNDI as an entry point to JDBC. This type of datasource
 * does not provide connection reuse; normally a pooled implementation is used.
 */
public class H2DataSourceTest {
    
    // a named database in memory supports multiple connections.
    private static final String CONNECT_STRING = "jdbc:h2:mem:mydb";
    
    @Before
    public void setUp() throws SQLException {
        // Since connections are saved in pool, you can no longer rely on
        // last connection closing the database.
        shutdownDatabaseAndDropAllTables();
    }
    
    @Test
    public void testBuildingConnectionFromDataSource() throws SQLException {
        DataSource dataSource = makeDataSource();
        makeConnection(dataSource);
    }
    
    @Test
    public void testNoLowLimitOnConnections() throws SQLException {
        DataSource dataSource = makeDataSource();
        int i;
        for (i = 0; i < 10000; i++) {
            makeConnection(dataSource);
        }
    }

    private static Connection makeConnection(DataSource dataSource) throws SQLException {
         return dataSource.getConnection();
    }

    private static DataSource makeDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(CONNECT_STRING);
        // user and password optional
        return dataSource;
    }
    
    
    @Test
    public void testInsertingTwoRowsAndCountingThemFromADifferentConnectionWithReadUncommitted() throws ClassNotFoundException, SQLException {
        DataSource dataSource = makeDataSource();
        Connection c = makeConnection(dataSource);
        c.setAutoCommit(false);
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        Connection c2 = makeConnection(dataSource);
        c2.setAutoCommit(false);
        c2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        try {
            createSomeTable(c);
            insertRow(c);
            insertRow(c);
            // but you can see uncommited data given suitably relaxed isolation.
            Assert.assertEquals(2, countSomeRows(c2));    
            c.rollback();
            Assert.assertEquals(0, countSomeRows(c2));
        } finally {
            c.close();
            c2.close();
        }
    }
    
    private static void shutdownDatabaseAndDropAllTables() throws SQLException {
        makeConnection(makeDataSource())
            .createStatement()
            .execute("shutdown immediately");
    }

    /** create some table for use in connection experiments */
    private void createSomeTable(Connection c) throws SQLException {
        PreparedStatement statement = c.prepareStatement(
                "create table post ("
                + "  subject varchar(256),"
                + "  content varchar(1024)"
                + ")"
        );
        statement.executeUpdate();
        statement.close();
    }
    
    private void insertRow(Connection c) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = c.prepareStatement("insert into post (subject, content) values (?, ?)");
            statement.setString(1, "aap");
            statement.setString(2, "noot");
            int rowCount = statement.executeUpdate();
            Assert.assertEquals(1, rowCount);
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private int countSomeRows(Connection c) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = c.prepareStatement("select subject, content from post");
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            return count;
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


}