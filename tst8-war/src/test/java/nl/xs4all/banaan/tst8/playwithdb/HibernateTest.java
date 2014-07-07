package nl.xs4all.banaan.tst8.playwithdb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import nl.xs4all.banaan.tst8.domain.Post;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.classic.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.stat.Statistics;
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
        SessionFactory sessionFactory = makeSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testHibernateStoreSomething() {
        SessionFactory sessionFactory = makeSessionFactory();
        Post post = makeSomePost("aap");
        savePostInSingleSession(sessionFactory, post);
        sessionFactory.close();
        Assert.assertEquals(new Long(1L), post.getId());
    }
    
    @Test
    public void testHibernateStoreSomethingAfterTweakingConfiguration() {
        SessionFactory sessionFactory = makeSessionFactoryWithDebugging();
        Post post = makeSomePost("aap");
        savePostInSingleSession(sessionFactory, post);
        sessionFactory.close();
        Assert.assertEquals(new Long(1L), post.getId());
    }

    
    @Test
    public void testHibernateStoreEndsUpInDatabase() throws Exception {
        DataSource dataSource = DbcpPoolBuilder.makeDataSourceReadCommited();
        SessionFactory sessionFactory = makeSessionFactory();
        Post post = makeSomePost("aap");
        savePostInSingleSession(sessionFactory, post);
        Assert.assertEquals(new Long(1L), post.getId());
        Assert.assertEquals(1, getPostCount(dataSource));
        sessionFactory.close();
    }
    
    @Test
    public void testHibernateStoreEndsUpInDatabaseButCanBeRolledBack() throws Exception {
        DataSource dataSource = DbcpPoolBuilder.makeDataSourceReadUncommited();
        SessionFactory sessionFactory = makeSessionFactory();
        Post post = makeSomePost("aap");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(post);
        // System.out.println("hallo"); 
        // printing makes junit fail go away.
        // so does skipping any of the previous tests
        // so does running in a debugger or maven rather than eclipse.
        // syncing the dbcp datasource doesnt help.
        // session.flush() does not help.
        // stacktrace says table not found
        Assert.assertEquals(1, getPostCount(dataSource));
        transaction.rollback();
        Assert.assertEquals(0, getPostCount(dataSource));
        session.close();
        // note how in-memory copy is not reverted when rolling back.
        Assert.assertEquals(new Long(1L), post.getId());

        sessionFactory.close();
    }
    
    @Test
    public void testHibernateStatistics() {
        SessionFactory sessionFactory = makeSessionFactory();
        Statistics statistics = sessionFactory.getStatistics();
        statistics.logSummary();
        sessionFactory.close();
    }
    
    @Test
    public void testHibernateCurrentConnectionWithThreadLocalSessionContext() {
        SessionFactory sessionFactory = makeSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Assert.assertEquals(false, session.getTransaction().isActive());
        Assert.assertEquals(false, sessionFactory.getCurrentSession().getTransaction().isActive());
        
        session.beginTransaction();
        Assert.assertEquals(true, session.getTransaction().isActive());
        Assert.assertEquals(true, sessionFactory.getCurrentSession().getTransaction().isActive());
        
        commitTransaction(sessionFactory);
        Assert.assertEquals(false, sessionFactory.getCurrentSession().getTransaction().isActive());
        Assert.assertFalse(session.isOpen());
        sessionFactory.close();
    }
    
    @Test
    public void testHibernateTransactionHelpers() {
        SessionFactory sessionFactory = makeSessionFactory();
        startTransaction(sessionFactory);
        Post post = makeSomePost("aap");
        sessionFactory.getCurrentSession().save(post);
        commitTransaction(sessionFactory);
        
        startTransaction(sessionFactory);
        Post post2 = (Post) sessionFactory.getCurrentSession().get(Post.class, post.getId());
        commitTransaction(sessionFactory);
        sessionFactory.close();
        
        Assert.assertEquals(post, post2);
        
        // nested transactions can be handled with an interceptor 
        // that only starts one when no transaction running.
    }
    
    @Test
    public void testMore() {
        // TODO: find out what aggresiveRelease promises
        new ConnectionProvider() {
    
                public void close() throws HibernateException {
                }
    
                public void closeConnection(Connection conn) throws SQLException {
                }
    
                public void configure(Properties props) throws HibernateException {
                }
    
                public Connection getConnection() throws SQLException {
                    return null;
                }
    
                public boolean supportsAggressiveRelease() {
                    return false;
                }
            };
    }
    
    
    @Test
    public void testHibernateDontNeedSaveAfterGet() {
        SessionFactory sessionFactory = makeSessionFactory();
        startTransaction(sessionFactory);
        Post post = makeSomePost("aap");
        sessionFactory.getCurrentSession().save(post);
        commitTransaction(sessionFactory);
        
        startTransaction(sessionFactory);
        Post post2 = (Post) sessionFactory.getCurrentSession().get(Post.class, post.getId());
        post2.setSubject("changed");
        commitTransaction(sessionFactory);
        
        startTransaction(sessionFactory);
        Post post3 = (Post) sessionFactory.getCurrentSession().get(Post.class, post.getId());
        Assert.assertEquals("changed", post3.getSubject());
        commitTransaction(sessionFactory);
        
        sessionFactory.close();
    }
    
    @Test
    public void testHibernateNoChangeAfterRollback() {
        SessionFactory sessionFactory = makeSessionFactory();
        startTransaction(sessionFactory);
        Post post = makeSomePost("aap");
        sessionFactory.getCurrentSession().save(post);
        commitTransaction(sessionFactory);
        
        startTransaction(sessionFactory);
        Post post2 = (Post) sessionFactory.getCurrentSession().get(Post.class, post.getId());
        post2.setSubject("changed");
        rollbackTransaction(sessionFactory);
        
        startTransaction(sessionFactory);
        Post post3 = (Post) sessionFactory.getCurrentSession().get(Post.class, post.getId());
        Assert.assertEquals("aap", post3.getSubject());
        commitTransaction(sessionFactory);
        
        sessionFactory.close();
    }

    private void rollbackTransaction(SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().getTransaction().rollback();        
    }

    private void commitTransaction(SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    private Transaction startTransaction(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession().beginTransaction();
    }

    
    private SessionFactory makeSessionFactory() {
        return new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
            .buildSessionFactory();
    }

    private SessionFactory makeSessionFactoryWithDebugging() {
        return new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .setProperty(Environment.SHOW_SQL, "true")
            .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
            .buildSessionFactory();
    }

    private void savePostInSingleSession(SessionFactory sessionFactory, Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();
    }
    
    private Post makeSomePost(String subject) {
        Post post = new Post();
        post.setSubject(subject);
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
