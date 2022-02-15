package dao.impl;

import dao.Dao;
import models.Unit;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class UnitDaoImpl implements Dao<Unit> {
    private Session session = null;
    @Override
    public Unit getByID(int id) {
        Unit unit = new Unit();
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

    @Override
    public List<Unit> getAll() {
        List<Unit> list = new ArrayList<>();
        try {
            session = HibernateSessionFactoryUtil.getSession();
            list = (List<Unit>) session.createQuery("From Unit ").list();
        } finally {
            session.close();
        }
        return list;
    }
}
