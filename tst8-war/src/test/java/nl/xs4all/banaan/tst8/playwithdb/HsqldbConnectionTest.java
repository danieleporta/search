package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class HsqldbConnectionTest {

    /** because of shutdown, database will be dropped when closing last connection */
    private static final String CONNECT_STRING = "jdbc:hsqldb:mem:mydb;shutdown=true";

    
    @Test
    public void testMakingConnectionViaDriverManager() throws ClassNotFoundException, SQLException {
        // most primitive form of connecting, load your own driver,
        // no way for JNDI to provide an alternative.
        // here as an in-memory database
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        c.close();
    }
    
    @Test
    public void testDoingDdl() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        try {
            createSomeTable(c);
        } finally {
            c.close();
        }
    }

    @Test
    public void testDoingCreatingTableAndReadingFromIt() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");

        try {
            createSomeTable(c);
            int count = countSomeRows(c);
            Assert.assertEquals(0, count);
        } finally {
            c.close();
        }
    }
    
    @Test
    public void testCreatingInsertingAndReadingFromIt() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");

        try {
            createSomeTable(c);
            insertRow(c);
            int count = countSomeRows(c);
            Assert.assertEquals(1, count);
        } finally {
            c.close();
        }
    }

    @Test
    public void testInsertingTwoRows() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");

        try {
            createSomeTable(c);
            insertRow(c);
            insertRow(c);
            int count = countSomeRows(c);
            Assert.assertEquals(2, count);
        } finally {
            c.close();
        }
    }
    
    @Test
    public void testInsertingTwoRowsAndCountingThemFromADifferentConnectionWithReadUncommitted() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        c.setAutoCommit(false);
        c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        Connection c2 = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        c2.setAutoCommit(false);
        c2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        try {
            createSomeTable(c);
            insertRow(c);
            insertRow(c);
            // with hsqldq 1.8, a different connection can see the write
            // before its committed, even if a request for complete serialisation
            // is processed without complaint.
            Assert.assertEquals(2, countSomeRows(c2));            
            c.rollback();
            Assert.assertEquals(0, countSomeRows(c2));
        } finally {
            c.close();
            c2.close();
        }
    }
    
    @Test
    public void testTransactionIsolationLevel() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        c.setAutoCommit(false);
        
        Assert.assertEquals(Connection.TRANSACTION_READ_COMMITTED, c.getTransactionIsolation());
        c.close();
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
}
