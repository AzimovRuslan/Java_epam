package dao.impl;

import dao.Dao;
import models.Unit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UnitDaoImpl implements Dao<Unit> {
    @Override
    public Unit getByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Unit.class, id);
    }

    @Override
    public void save(Unit unit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(unit);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Unit unit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(unit);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Unit unit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(unit);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Unit> getAll() {
        return (List<Unit>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Unit ").list();
    }
}
