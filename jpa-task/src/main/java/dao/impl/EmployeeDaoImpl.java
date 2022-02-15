package dao.impl;

import dao.Dao;
import models.Employee;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements Dao<Employee> {
    private Session session = null;

    @Override
    public Employee getByID(int id) {
        Employee employee = new Employee();
        try {
            session = HibernateSessionFactoryUtil.getSession();
            employee = session.get(Employee.class, id);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try {
            session = HibernateSessionFactoryUtil.getSession();
            list = (List<Employee>) session.createQuery("From Employee ").list();
        } finally {
            session.close();
        }
        return list;
    }
}
