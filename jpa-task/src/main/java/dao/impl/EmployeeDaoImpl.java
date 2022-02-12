package dao.impl;

import dao.Dao;
import models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.transaction.Transactional;
import java.util.List;

public class EmployeeDaoImpl implements Dao<Employee> {
    @Override
    public Employee getByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Employee").list();
    }
}
