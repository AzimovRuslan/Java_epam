package dao.impl;

import dao.Dao;
import models.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ProjectDaoImpl implements Dao<Project> {
    @Override
    public Project getByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Project.class, id);
    }

    @Override
    public void save(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(project);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(project);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(project);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Project> getAll() {
        return (List<Project>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Project ").list();
    }
}
