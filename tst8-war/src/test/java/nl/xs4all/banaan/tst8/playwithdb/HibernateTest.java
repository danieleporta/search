package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import nl.xs4all.banaan.tst8.domain.Post;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

    @Before
    public void setUp() throws SQLException {
        DbcpPoolBuilder.shutdownDatabaseAndDropAllTables();
    }
    
    @Test
    public void testHibernateSessionDoingNothing() {
        SessionFactory sessionFactory = new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testHibernateStoreSomething() {
        SessionFactory sessionFactory = new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .buildSessionFactory();
        Post post = makeSomePost();
        savePost(sessionFactory, post);
        sessionFactory.close();
        Assert.assertEquals(new Long(1L), post.getId());
    }
    
    @Test
    public void testHibernateStoreEndsUpInDatabase() throws Exception {
        DataSource dataSource = DbcpPoolBuilder.makeDataSourceReadCommited();
        SessionFactory sessionFactory = new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .buildSessionFactory();
        Post post = makeSomePost();
        savePost(sessionFactory, post);
        Assert.assertEquals(new Long(1L), post.getId());
        Assert.assertEquals(1, getPostCount(dataSource));
        sessionFactory.close();
    }
    
    @Test
    public void testHibernateStoreEndsUpInDatabaseButCanBeRolledBack() throws Exception {
        DataSource dataSource = DbcpPoolBuilder.makeDataSourceReadUncommited();
        SessionFactory sessionFactory = new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .buildSessionFactory();
        Post post = makeSomePost();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(post);
        Assert.assertEquals(1, getPostCount(dataSource));
        transaction.rollback();
        Assert.assertEquals(0, getPostCount(dataSource));
        session.close();
        // note how in-memory copy is not reverted when rolling back.
        Assert.assertEquals(new Long(1L), post.getId());

        sessionFactory.close();
    }

    private void savePost(SessionFactory sessionFactory, Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();
    }
    
    private Post makeSomePost() {
        Post post = new Post();
        post.setSubject("aap");
        post.setContent("noot");
        return post;
    }

    private int getPostCount(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        int rowCount = getRowCount(connection);
        connection.close();
        return rowCount;
    }
    
    private int getRowCount(Connection c) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = c.prepareStatement("select count(*) from post");
            ResultSet resultSet = statement.executeQuery();
            int count;
            if ( !resultSet.next()) {
                throw new RuntimeException("can't count");
            }
            System.out.println(resultSet);
            count = resultSet.getInt(1);
            if (resultSet.next()) {
                throw new RuntimeException("multiple results");
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
