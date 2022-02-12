package utils;

import models.Employee;
import models.EmployeePersonalInfo;
import models.Project;
import models.Unit;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

public class HibernateSessionFactoryUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(EmployeePersonalInfo.class);
                configuration.addAnnotatedClass(Unit.class);
                configuration.addAnnotatedClass(Project.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
