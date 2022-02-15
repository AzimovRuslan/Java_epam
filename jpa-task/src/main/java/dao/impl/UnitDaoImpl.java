package dao.impl;

import dao.Dao;
import models.Unit;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

public class UnitDaoImpl implements Dao<Unit> {
    private Session session = null;

    @Override
    public Unit getByID(int id) {
        Unit unit;
        try {
            session = HibernateSessionFactoryUtil.getSession();
            unit = session.get(Unit.class, id);
        } finally {
            session.close();
        }
        return unit;
    }

    @Override
    public void save(Unit unit) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.save(unit);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Unit unit) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.update(unit);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Unit unit) {
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.delete(unit);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
