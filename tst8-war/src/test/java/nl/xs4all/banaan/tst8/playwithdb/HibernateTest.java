package nl.xs4all.banaan.tst8.playwithdb;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
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

}
