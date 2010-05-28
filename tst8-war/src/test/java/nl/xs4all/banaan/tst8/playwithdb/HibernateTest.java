package nl.xs4all.banaan.tst8.playwithdb;


import nl.xs4all.banaan.tst8.domain.Post;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.Test;

public class HibernateTest {

    
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
        Session session = sessionFactory.openSession();
        Post post = new Post();
        post.setSubject("aap");
        post.setContent("noot");
        session.save(post);
        session.close();
        sessionFactory.close();
        Assert.assertEquals(new Long(1L), post.getId());
    }
    
    @Test
    public void testHibernateStoreEndsUpInDatabase() {
        // just show you can use the database twice
        SessionFactory sessionFactory = new Configuration()
            .configure("/test-hibernate.cfg.xml")
            .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Post post = new Post();
        post.setSubject("aap");
        post.setContent("noot");
        session.save(post);
        session.close();
        sessionFactory.close();
        Assert.assertEquals(new Long(1L), post.getId());
    }
    
    
}
