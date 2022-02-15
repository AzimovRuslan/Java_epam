package utils;

import models.Employee;
import models.EmployeePersonalInfo;
import models.Project;
import models.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);
    private static SessionFactory sessionFactory = null;

    private HibernateSessionFactoryUtil() {
    }

    static {
        try {
            sessionFactory = getSessionFactory();
        } catch (RuntimeException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(EmployeePersonalInfo.class);
            configuration.addAnnotatedClass(Unit.class);
            configuration.addAnnotatedClass(Project.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            sessionFactory.openSession();
            return sessionFactory;
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
