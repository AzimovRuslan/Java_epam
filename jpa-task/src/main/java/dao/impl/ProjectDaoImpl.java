package dao.impl;

import dao.Dao;
import models.Project;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

public class ProjectDaoImpl implements Dao<Project> {
    private Session session = null;

    @Override
    public Project getByID(int id) {
        Project project;
        try {
            session = HibernateSessionFactoryUtil.getSession();
            project = session.get(Project.class, id);
        } finally {
            session.close();
        }
        return project;
    }

    @Override
    public void save(Project project) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Project project) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Project project) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.delete(project);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
