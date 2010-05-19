package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.jdbc.JdbcSQLException;
import org.junit.Assert;
import org.junit.Test;

public class H2ConnectionTest {

    private static final String DRIVER = "org.h2.Driver";
    
    // a named database in memory supports multiple connections.
    // by default, last connection closes database.
    private static final String CONNECT_STRING = "jdbc:h2:mem:mydb";

    
    @Test
    public void testMakingConnectionViaDriverManager() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
        c.close();
    }
    
    @Test
    public void testDoingDdl() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
        try {
            createSomeTable(c);
        } finally {
            c.close();
        }
    }

    @Test
    public void testDoingCreatingTableAndReadingFromIt() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
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
        Connection c = makeConnection();
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
        Connection c = makeConnection();

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
    public void testInsertingTwoRowsAndCountingThemFromADifferentConnectionWithReadCommitted() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
        c.setAutoCommit(false);
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        Connection c2 = makeConnection();
        c2.setAutoCommit(false);
        c2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        try {
            createSomeTable(c);
            insertRow(c);
            insertRow(c);
            try {
                // H2 has table-level locking for transactions,
                // no opportunity to see old commited data if a new transaction
                // is in progress.
                Assert.assertEquals(2, countSomeRows(c2));    
                Assert.fail("They implemented MVCC and didnt tell me");
            }
            catch (JdbcSQLException ok) {}
            c.rollback();
            Assert.assertEquals(0, countSomeRows(c2));
        } finally {
            c.close();
            c2.close();
        }
    }
    
    @Test
    public void testInsertingTwoRowsAndCountingThemFromADifferentConnectionWithReadUncommitted() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
        c.setAutoCommit(false);
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        Connection c2 = makeConnection();
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
    
    @Test
    public void testTransactionIsolationLevel() throws ClassNotFoundException, SQLException {
        Connection c = makeConnection();
        c.setAutoCommit(false);
        
        Assert.assertEquals(Connection.TRANSACTION_READ_COMMITTED, c.getTransactionIsolation());
        c.close();
    }
    

    private Connection makeConnection() throws ClassNotFoundException,
            SQLException {
        // most primitive form of connecting, load your own driver,
        // no way for JNDI to provide an alternative.
        // here as an in-memory database
        Class.forName(DRIVER);
        Connection c = DriverManager.getConnection(CONNECT_STRING, "SA", "");
        return c;
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
